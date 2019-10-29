package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

import java.util.function.Consumer;

public class PgAuthImpl implements PgAuth {

  private PgPool client;
  private String authenticateQuery = PG_DEFAULT_AUTHENTICATE_QUERY;
  private String rolesQuery = PG_DEFAULT_ROLES_QUERY;
  private String permissionsQuery = PG_DEFAULT_PERMISSIONS_QUERY;
  private String rolePrefix = PG_DEFAULT_ROLE_PREFIX;
  private PgHashStrategy strategy;
  private Vertx vertx;

  public PgAuthImpl(Vertx vertx, PgPool client) {
    this.vertx = vertx;
    this.client = client;
    strategy = PgHashStrategy.createPBKDF2(vertx);
  }

  @Override
  public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
    String username = authInfo.getString("username");
    if (username == null) {
      resultHandler.handle(Future.failedFuture("authInfo must contain username in 'username' field"));
      return;
    }
    String password = authInfo.getString("password");
    if (password == null) {
      resultHandler.handle(Future.failedFuture("authInfo must contain password in 'password' field"));
      return;
    }
    executeQuery(authenticateQuery, Tuple.of(username), resultHandler, rs -> {
      RowIterator<Row> nextRow = rs.iterator();
      if (nextRow.hasNext()) {
        Row row = nextRow.next();
        String hashedStoredPwd = strategy.getHashedStoredPwd(row);
        String salt = strategy.getSalt(row);
        // extract the version (-1 means no version)
        int version = -1;
        int sep = hashedStoredPwd.lastIndexOf('$');
        if (sep != -1) {
          try {
            version = Integer.parseInt(hashedStoredPwd.substring(sep + 1));
          } catch (NumberFormatException e) {
            // the nonce version is not a number
            resultHandler.handle(Future.failedFuture("Invalid nonce version: " + version));
            return;
          }
        }
        String hashedPassword = strategy.computeHash(password, salt, version);
        if (PgHashStrategy.isEqual(hashedStoredPwd, hashedPassword)) {
          resultHandler.handle(Future.succeededFuture(new PgUser(username, this, rolePrefix)));
        } else {
          resultHandler.handle(Future.failedFuture("Invalid username/password"));
        }
      } else {
        resultHandler.handle(Future.failedFuture("Invalid username/password"));
      }
    });
  }

  @Override
  public PgAuth setAuthenticationQuery(String authenticationQuery) {
    this.authenticateQuery = authenticationQuery;
    return this;
  }

  @Override
  public PgAuth setRolesQuery(String rolesQuery) {
    this.rolesQuery = rolesQuery;
    return this;
  }

  @Override
  public PgAuth setPermissionsQuery(String permissionsQuery) {
    this.permissionsQuery = permissionsQuery;
    return this;
  }

  @Override
  public PgAuth setRolePrefix(String rolePrefix) {
    this.rolePrefix = rolePrefix;
    return this;
  }

  @Override
  public PgAuth setHashStrategy(PgHashStrategy strategy) {
    this.strategy = strategy;
    return this;
  }

  <T> void executeQuery(String query, Tuple params, Handler<AsyncResult<T>> resultHandler,
                        Consumer<RowSet<Row>> resultSetConsumer) {
    client.preparedQuery(query, params, queryRes -> {
      if (queryRes.succeeded()) {
        RowSet<Row> rs = queryRes.result();
        resultSetConsumer.accept(rs);
      } else {
        resultHandler.handle(Future.failedFuture(queryRes.cause()));
      }
    });
  }


  @Override
  public String computeHash(String password, String salt, int version) {
    return strategy.computeHash(password, salt, version);
  }

  @Override
  public String generateSalt() {
    return strategy.generateSalt();
  }

  @Override
  public PgAuth setNonces(JsonArray nonces) {
    strategy.setNonces(nonces);
    return this;
  }

  String getRolesQuery() {
    return rolesQuery;
  }

  String getPermissionsQuery() {
    return permissionsQuery;
  }
}
