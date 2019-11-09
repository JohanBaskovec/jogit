package com.example.starter;

import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
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
      failWithNonGrpcError(t);
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
            failWithNonGrpcError(event.cause());
          } else {
            transaction = event.result();
            handler.handle(event.result());
          }
        } catch (Throwable t) {
          failWithNonGrpcError(t);
        }
      }
    });
  }

  private void failWithNonGrpcError(Throwable t) {
    t.printStackTrace();
    fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
  }

  private void fail(Throwable t) {
    if (transaction != null) {
      transaction.rollback(new Handler<AsyncResult<Void>>() {
        @Override
        public void handle(AsyncResult<Void> event) {
          if (event.failed()) {
            System.out.println("Error during rollback!");
            failWithNonGrpcError(event.cause());
          } else {
            future.handle(Future.failedFuture(t));
          }
        }
      });
    } else {
      future.handle(Future.failedFuture(t));
    }
  }

  private void complete(ResponseType response) {
    if (this.transaction != null) {
      this.transaction.commit((AsyncResult<Void> commitResult) -> {
        if (commitResult.failed()) {
          System.out.println("Error during commit!");
          failWithNonGrpcError(commitResult.cause());
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
      // if this is a gRPC error, then we can submit the Exception as-is,
      // (this is an "expected" Exception, such as invalid request parameters,
      // or unauthenticated user), otherwise we send a gRPC Status.INTERNAL Exception.
      if (event.cause() instanceof StatusException
        || event.cause() instanceof StatusRuntimeException) {
        fail(event.cause());
      } else {
        failWithNonGrpcError(event.cause());
      }
    } else {
      complete(event.result());
    }
  }
}
