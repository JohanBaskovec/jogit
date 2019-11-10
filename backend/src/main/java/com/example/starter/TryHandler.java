package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public abstract class TryHandler<ResultType, HandlerResultType> implements Handler<AsyncResult<ResultType>> {
  protected final Handler<AsyncResult<HandlerResultType>> handler;

  TryHandler(Handler<AsyncResult<HandlerResultType>> handler) {
    this.handler = handler;
  }

  @Override
  public void handle(AsyncResult<ResultType> result) {
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

  public abstract void onSuccess(ResultType result);

  public abstract void onFailure(Throwable e);
}
