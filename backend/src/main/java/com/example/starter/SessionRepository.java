package com.example.starter;

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

public class SessionRepository {
  private static String selectByIdQuery;
  private static String insertQuery;
  private final Transaction transaction;
  private final SessionService sessionService;

  static {
    try {
      selectByIdQuery = StaticFileSystemService.readResourceToString("sql/session/select.sql");
      insertQuery = StaticFileSystemService.readResourceToString("sql/session/insert.sql");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  SessionRepository(
    Transaction transaction,
    SessionService sessionService
  ) {
    this.transaction = transaction;
    this.sessionService = sessionService;
  }

  void getSessionById(String id, Handler<AsyncResult<Session>> handler) {
    transaction.preparedQuery(
      selectByIdQuery,
      Tuple.of(id),
      new Handler<AsyncResult<RowSet<Row>>>() {
        @Override
        public void handle(AsyncResult<RowSet<Row>> maybeResult) {
          if (maybeResult.failed()) {
            System.out.println(selectByIdQuery + " failed.");
            maybeResult.cause().printStackTrace();
            handler.handle(Future.failedFuture(maybeResult.cause()));
            return;
          }

          RowSet<Row> rows = maybeResult.result();
          if (rows.size() == 0) {
            System.out.println(selectByIdQuery + " returned nothing.");
            handler.handle(Future.succeededFuture());
            return;
          }
          RowIterator<Row> it = rows.iterator();
          Row row = it.next();
          Session session = sessionService.fromRow(row);
          handler.handle(Future.succeededFuture(session));
        }
      });
  }

  public void insert(Session session, Handler<AsyncResult<Void>> handler) {
    transaction.preparedQuery(
      insertQuery,
      Tuple.of(session.getUserUsername(), session.getId()),
      new ErrorHandler<RowSet<Row>, Void>(handler) {
        @Override
        public void handleSuccess(RowSet<Row> result) {
          handler.handle(Future.succeededFuture(null));
        }
      });
  }
}
