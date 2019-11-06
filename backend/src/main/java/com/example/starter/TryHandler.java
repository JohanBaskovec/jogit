package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public abstract class TryHandler<T, U> implements Handler<AsyncResult<T>> {
  protected final Handler<AsyncResult<U>> handler;

  TryHandler(Handler<AsyncResult<U>> handler) {
    this.handler = handler;
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
