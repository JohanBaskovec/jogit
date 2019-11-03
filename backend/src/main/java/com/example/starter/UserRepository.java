package com.example.starter;

import com.example.starter.gprc.User;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.Tuple;

import java.io.IOException;

public class UserRepository {
  private static String selectByUsernameQuery;
  private static String insertQuery;
  private final Transaction transaction;
  private final UserService userService;

  static {
    try {
      selectByUsernameQuery = StaticFileSystemService.readResourceToString("sql/user/select_by_username.sql");
      insertQuery = StaticFileSystemService.readResourceToString("sql/user/insert.sql");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  UserRepository(
    Transaction transaction,
    UserService userService
  ) {
    this.transaction = transaction;
    this.userService = userService;
  }

  void getByUsername(String username, Handler<AsyncResult<User>> handler) {
    transaction.preparedQuery(
      selectByUsernameQuery,
      Tuple.of(username),
      new Handler<AsyncResult<RowSet<Row>>>() {
        @Override
        public void handle(AsyncResult<RowSet<Row>> maybeResult) {
          if (maybeResult.failed()) {
            System.out.println(selectByUsernameQuery + " failed.");
            maybeResult.cause().printStackTrace();
            handler.handle(Future.failedFuture(maybeResult.cause()));
            return;
          }

          RowSet<Row> rows = maybeResult.result();
          if (rows.size() == 0) {
            System.out.println(selectByUsernameQuery + " returned nothing.");
            handler.handle(Future.succeededFuture());
            return;
          }
          RowIterator<Row> it = rows.iterator();
          Row row = it.next();
          User user = userService.fromRow(row);
          handler.handle(Future.succeededFuture(user));
        }
      });
  }

  void insert(User user, Handler<AsyncResult<Void>> handler) {
    transaction.preparedQuery(
      insertQuery,
      Tuple.of(user.getUsername(), user.getPassword(), user.getPasswordSalt()),
      new Handler<AsyncResult<RowSet<Row>>>() {
        @Override
        public void handle(AsyncResult<RowSet<Row>> maybeResult) {
          // TODO: better error handling when username already used
          if (maybeResult.failed()) {
            System.out.println(selectByUsernameQuery + " failed.");
            maybeResult.cause().printStackTrace();
            handler.handle(Future.failedFuture(maybeResult.cause()));
            return;
          }
          handler.handle(Future.succeededFuture());
        }
      });
  }
}
