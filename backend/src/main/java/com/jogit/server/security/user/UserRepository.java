package com.jogit.server.security.user;

import com.jogit.server.fs.StaticFileSystemService;
import com.jogit.server.grpc.DeleteSshPublicKeyReply;
import com.jogit.server.grpc.User;
import com.jogit.server.grpc.SshPublicKey;
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
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  public static class SshKeySelectWhere {
    private String username;
    private Integer id;
    private Boolean deleted;

    public boolean isEmpty() {
      return username == null && id == null && deleted == null;
    }

    public String getUsername() {
      return username;
    }

    public SshKeySelectWhere setUsername(String username) {
      this.username = username;
      return this;
    }

    public Integer getId() {
      return id;
    }

    public SshKeySelectWhere setId(Integer id) {
      this.id = id;
      return this;
    }

    public Boolean getDeleted() {
      return deleted;
    }

    public SshKeySelectWhere setDeleted(Boolean deleted) {
      this.deleted = deleted;
      return this;
    }
  }

  private static String selectByUsernameQuery;
  private static String insertQuery;
  private static String updateQuery;
  private final Transaction transaction;
  private final UserService userService;
  private final LinuxService linuxService;

  //language=MySQL
  private static final String insertSshQuery = " insert into ssh_key(ssh_key, user_username) values($1, $2)";
  //language=MySQL
  private static final String deleteSshQuery = " update ssh_key set deleted=true where id=$1";
  //language=MySQL
  private static final String selectSshQuery = " select id, ssh_key, user_username, deleted from ssh_key ";

  static {
    try {
      selectByUsernameQuery = StaticFileSystemService.readResourceToString("sql/user/select_by_username.sql");
      insertQuery = StaticFileSystemService.readResourceToString("sql/user/insert.sql");
      updateQuery = StaticFileSystemService.readResourceToString("sql/user/update.sql");
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

  public void update(User user, Handler<AsyncResult<Void>> handler) {
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

  public void addSshKeys(User user, SshPublicKey key, Handler<AsyncResult<Void>> handler) {
    transaction.preparedQuery(
      insertSshQuery,
      Tuple.of(key.getKey(), user.getUsername()),
      new ErrorHandler<RowSet<Row>, Void>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> result) {
          handler.handle(Future.succeededFuture());
        }
      });
  }

  public void deleteSshKey(int id, Handler<AsyncResult<Void>> handler) {
    transaction.preparedQuery(
      deleteSshQuery,
      Tuple.of(id),
      new ErrorHandler<RowSet<Row>, Void>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> result) {
          handler.handle(Future.succeededFuture());
        }
      });
  }

  public void updateSshKey(SshPublicKey key, Handler<AsyncResult<Void>> handler) {
    transaction.preparedQuery(
      //language=MySQL
      " update ssh_key set ssh_key = $1 where id = $2",
      Tuple.of(key.getKey(), key.getId()),
      new ErrorHandler<RowSet<Row>, Void>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> result) {
          handler.handle(Future.succeededFuture());
        }
      });
  }

  public void getSshKeys(SshKeySelectWhere where, Handler<AsyncResult<List<SshPublicKey>>> handler) {
    String query = selectSshQuery;
    Tuple tuple = Tuple.tuple();
    boolean previous = false;
    if (!where.isEmpty()) {
      query += " where ";
      if (where.id != null) {
        tuple.addInteger(where.id);
        query += " id = $" + tuple.size();
        previous = true;
      }
      if (where.deleted != null) {
        tuple.addBoolean(where.deleted);
        if (previous) {
          query += " and ";
        }
        query += " deleted = $" + tuple.size();
        previous = true;
      }
      if (where.username != null) {
        tuple.addString(where.username);
        if (previous) {
          query += " and ";
        }
        query += " user_username = $" + tuple.size();
        previous = true;
      }
    }

      transaction.preparedQuery(
      query,
      tuple,
      new ErrorHandler<RowSet<Row>, List<SshPublicKey>>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> rows) {
          List<SshPublicKey> sshKeys = new ArrayList<>();
          for (Row row : rows) {
            SshPublicKey key = SshPublicKey.newBuilder()
              .setDeleted(row.getBoolean("deleted"))
              .setId(row.getInteger("id"))
              .setUserUsername(row.getString("user_username"))
              .setKey(row.getString("ssh_key"))
              .build();
            sshKeys.add(key);
          }
          handler.handle(Future.succeededFuture(sshKeys));
        }
      });
  }
}
