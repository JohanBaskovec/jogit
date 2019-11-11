package com.jogit.server.security.user;

import com.jogit.server.fs.StaticFileSystemService;
import com.jogit.server.gprc.User;
import com.jogit.server.linux.LinuxService;
import com.jogit.server.vertx.ErrorHandler;
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
  private final LinuxService linuxService;

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
    UserService userService,
    LinuxService linuxService
  ) {
    this.transaction = transaction;
    this.userService = userService;
    this.linuxService = linuxService;
  }

  public void getByUsername(String username, Handler<AsyncResult<User>> handler) {
    transaction.preparedQuery(
      selectByUsernameQuery,
      Tuple.of(username),
      new ErrorHandler<RowSet<Row>, User>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> rows) {
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

  public void insert(User user, Handler<AsyncResult<Void>> handler) {
    transaction.preparedQuery(
      insertQuery,
      Tuple.of(user.getUsername(), user.getPassword(), user.getPasswordSalt()),
      new ErrorHandler<RowSet<Row>, Void>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> result) {
          linuxService.createUserAccount(user);
          handler.handle(Future.succeededFuture());
        }
      });
  }
}
