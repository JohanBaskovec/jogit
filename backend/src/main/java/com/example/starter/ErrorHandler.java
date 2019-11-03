package com.example.starter;

import io.grpc.Status;
import io.vertx.core.Future;

public abstract class ErrorHandler<T, U> extends TryHandler<T, U> {
  ErrorHandler(Future<U> future) {
    super(future);
  }

  @Override
  public void onFailure(Throwable e) {
    e.printStackTrace();
    future.fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
  }

  @Override
  public void onSuccess(T result) {
    handleSuccess(result);
  }

  public abstract void handleSuccess(T result);
}
