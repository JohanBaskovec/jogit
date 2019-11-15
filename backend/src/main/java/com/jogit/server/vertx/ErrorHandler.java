package com.jogit.server.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public abstract class ErrorHandler<ResultType, HandlerResultType> implements Handler<AsyncResult<ResultType>> {
  protected final Handler<AsyncResult<HandlerResultType>> handler;
  public ErrorHandler(Handler<AsyncResult<HandlerResultType>> handler) {
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

  public void onFailure(Throwable e) {
    handler.handle(Future.failedFuture(e));
  }

  public void onSuccess(ResultType result) {
    handleSuccess(result);
  }

  public abstract void handleSuccess(ResultType result);
}
