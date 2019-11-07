package com.example.starter;

import io.grpc.Status;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

public class RequestContext<RequestType, ResponseType> implements Handler<AsyncResult<ResponseType>> {
  private final PgPool pgClient;
  private final RequestType request;
  private final Future<ResponseType> future;
  private Transaction transaction;

  public RequestContext(PgPool pgClient, RequestType request, Future<ResponseType> future) {
    this.pgClient = pgClient;
    this.request = request;
    this.future = future;
  }

  public void run(Runnable runnable) {
    try {
      runnable.run();
    } catch (Throwable t) {
      this.fail(t);
    }
  }

  public RequestType getRequest() {
    return request;
  }

  private Future<ResponseType> getFuture() {
    return future;
  }

  public void begin(Handler<Transaction> handler) {
    pgClient.begin(new Handler<AsyncResult<Transaction>>() {
      @Override
      public void handle(AsyncResult<Transaction> event) {
        try {
          if (event.failed()) {
            fail(event.cause());
          } else {
            transaction = event.result();
            handler.handle(event.result());
          }
        } catch (Throwable t) {
          fail(t);
        }
      }
    });
  }

  private void fail(Throwable t) {
    t.printStackTrace();
    if (transaction != null) {
      transaction.rollback(new Handler<AsyncResult<Void>>() {
        @Override
        public void handle(AsyncResult<Void> event) {
          if (event.failed()) {
            System.out.println("Error during rollback!");
            event.cause().printStackTrace();
          }
          future.handle(Future.failedFuture(Status.INTERNAL.withDescription("Internal error.").asRuntimeException()));
        }
      });
    } else {
      future.handle(Future.failedFuture(Status.INTERNAL.withDescription("Internal error.").asRuntimeException()));
    }
  }

  private void complete(ResponseType response) {
    if (this.transaction != null) {
      this.transaction.commit((AsyncResult<Void> commitResult) -> {
        if (commitResult.failed()) {
          System.out.println("Error during commit!");
          commitResult.cause().printStackTrace();
          future.handle(Future.failedFuture(Status.INTERNAL.withDescription("Internal error.").asRuntimeException()));
        } else {
          future.handle(Future.succeededFuture(response));
        }
      });
    }
  }

  /**
   * Something has happened, so handle it.
   *
   * @param event the event to handle
   */
  @Override
  public void handle(AsyncResult<ResponseType> event) {
    if (event.failed()) {
      fail(event.cause());
    } else {
      complete(event.result());
    }
  }
}
