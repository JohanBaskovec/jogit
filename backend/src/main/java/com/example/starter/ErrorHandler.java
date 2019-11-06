package com.example.starter;

import io.grpc.Status;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public abstract class ErrorHandler<T, U> extends TryHandler<T, U> {
  ErrorHandler(Handler<AsyncResult<U>> handler) {
    super(handler);
  }

  @Override
  public void onFailure(Throwable e) {
    e.printStackTrace();
    handler.handle(Future.failedFuture(Status.INTERNAL.withDescription("Internal error.").asRuntimeException()));
  }

  @Override
  public void onSuccess(T result) {
    try {
      handleSuccess(result);
    } catch (Throwable t) {
      onFailure(t);
    }
  }

  public abstract void handleSuccess(T result);
}
