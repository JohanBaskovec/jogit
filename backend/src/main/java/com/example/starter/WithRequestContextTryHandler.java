package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public abstract class WithRequestContextTryHandler<RequestType, ResponseType, ResultType> implements Handler<AsyncResult<ResultType>> {
  protected final RequestContext<RequestType, ResponseType> requestContext;

  WithRequestContextTryHandler(RequestContext<RequestType, ResponseType> requestContext) {
    this.requestContext = requestContext;
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
