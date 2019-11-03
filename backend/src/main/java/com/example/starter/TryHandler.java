package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public abstract class TryHandler<T, U> implements Handler<AsyncResult<T>> {
  protected final Future<U> future;

  TryHandler(Future<U> future) {
    this.future = future;
  }

  @Override
  public void handle(AsyncResult<T> result) {
    try {
      if (result.failed()) {
        onFailure(result.cause());
      } else {
        onSuccess(result.result());
      }
    } catch (Throwable e) {
      onFailure(e);
    }
  }

  public abstract void onSuccess(T result);

  public abstract void onFailure(Throwable e);
}
