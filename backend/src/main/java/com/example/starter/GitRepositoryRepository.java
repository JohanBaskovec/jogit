package com.example.starter;

import com.example.starter.gprc.GitRepository;
import com.example.starter.gprc.Session;
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

public class GitRepositoryRepository {
  private static String selectByUserNameQuery;
  private final Transaction transaction;
  private final GitRepositoryService gitRepositoryService;

  private static String insertQuery ;

  static {
    try {
      selectByUserNameQuery = StaticFileSystemService.readResourceToString("sql/git_repository/select_by_username.sql");
      insertQuery = StaticFileSystemService.readResourceToString("sql/git_repository/insert.sql");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  GitRepositoryRepository(
    Transaction transaction,
    GitRepositoryService gitRepositoryService
  ) {
    this.transaction = transaction;
    this.gitRepositoryService = gitRepositoryService;
  }

  void getByUsername(String username, Handler<AsyncResult<List<GitRepository>>> handler) {
    transaction.preparedQuery(
      selectByUserNameQuery,
      Tuple.of(username),
      new ErrorHandler<RowSet<Row>, List<GitRepository>>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> rows) {
          List<GitRepository> result = new ArrayList<>();
          for (Row row : rows) {
            result.add(gitRepositoryService.gitRepositoryFromRow(row));
          }
          handler.handle(Future.succeededFuture(result));
        }
      });
  }

  public void insert(GitRepository gitRepository, Handler<AsyncResult<Void>> handler) {
    transaction.preparedQuery(
      insertQuery,
      Tuple.of(gitRepository.getName(), gitRepository.getUserUserName()),
      new ErrorHandler<RowSet<Row>, Void>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> result) {
          gitRepositoryService.createRepository(
            gitRepository.getUserUserName(),
            gitRepository.getName()
          );
          handler.handle(Future.succeededFuture(null));
        }
      });
  }
}
