package com.jogit.server.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public abstract class ErrorHandler<ResultType, HandlerResultType> extends TryHandler<ResultType, HandlerResultType> {
  public ErrorHandler(Handler<AsyncResult<HandlerResultType>> handler) {
    super(handler);
  }

  @Override
  public void onFailure(Throwable e) {
    handler.handle(Future.failedFuture(e));
  }

  @Override
  public void onSuccess(ResultType result) {
    handleSuccess(result);
  }

  public abstract void handleSuccess(ResultType result);
}
