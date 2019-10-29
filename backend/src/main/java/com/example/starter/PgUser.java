package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;

/**
 * Fake User, temporary for testing.
 * TODO: replace with PgUser (or other
 */
public class PgUser extends AbstractUser  {
  private PgAuthImpl authProvider;
  private String username;
  private JsonObject principal;

  private String rolePrefix;

  public PgUser() {
  }

  PgUser(String username, PgAuthImpl authProvider, String rolePrefix) {
    this.username = username;
    this.authProvider = authProvider;
    this.rolePrefix = rolePrefix;
  }

  @Override
  public void doIsPermitted(String permissionOrRole, Handler<AsyncResult<Boolean>> resultHandler) {
    if (permissionOrRole != null && permissionOrRole.startsWith(rolePrefix)) {
      hasRoleOrPermission(permissionOrRole.substring(rolePrefix.length()), authProvider.getRolesQuery(), resultHandler);
    } else {
      hasRoleOrPermission(permissionOrRole, authProvider.getPermissionsQuery(), resultHandler);
    }
  }

  @Override
  public JsonObject principal() {
    if (principal == null) {
      principal = new JsonObject().put("username", username);
    }
    return principal;
  }

  @Override
  public void setAuthProvider(AuthProvider authProvider) {
    if (authProvider instanceof PgAuthImpl) {
      this.authProvider = (PgAuthImpl)authProvider;
    } else {
      throw new IllegalArgumentException("Not a PgAuthImpl");
    }
  }

  @Override
  public void writeToBuffer(Buffer buff) {
    super.writeToBuffer(buff);
    byte[] bytes = username.getBytes(StandardCharsets.UTF_8);
    buff.appendInt(bytes.length);
    buff.appendBytes(bytes);

    bytes = rolePrefix.getBytes(StandardCharsets.UTF_8);
    buff.appendInt(bytes.length);
    buff.appendBytes(bytes);
  }

  @Override
  public int readFromBuffer(int pos, Buffer buffer) {
    pos = super.readFromBuffer(pos, buffer);
    int len = buffer.getInt(pos);
    pos += 4;
    byte[] bytes = buffer.getBytes(pos, pos + len);
    username = new String(bytes, StandardCharsets.UTF_8);
    pos += len;

    len = buffer.getInt(pos);
    pos += 4;
    bytes = buffer.getBytes(pos, pos + len);
    rolePrefix = new String(bytes, StandardCharsets.UTF_8);
    pos += len;

    return pos;
  }

  private void hasRoleOrPermission(String roleOrPermission, String query, Handler<AsyncResult<Boolean>> resultHandler) {
    authProvider.executeQuery(query, Tuple.of(username), resultHandler, (RowSet<Row> rs) -> {
      boolean has = false;
      for (Row result : rs) {
        String theRoleOrPermission = result.getString(0);
        if (roleOrPermission.equals(theRoleOrPermission)) {
          resultHandler.handle(Future.succeededFuture(true));
          has = true;
          break;
        }
      }
      if (!has) {
        resultHandler.handle(Future.succeededFuture(false));
      }
    });
  }
}
