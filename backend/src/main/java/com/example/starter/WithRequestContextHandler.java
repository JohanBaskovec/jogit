package com.example.starter;

import io.vertx.core.Future;

public abstract class WithRequestContextHandler<RequestType, ResponseType, ResultType> extends WithRequestContextTryHandler<RequestType, ResponseType, ResultType> {
  WithRequestContextHandler(RequestContext<RequestType, ResponseType> requestContext) {
    super(requestContext);
  }

  @Override
  public void onFailure(Throwable e) {
    requestContext.handle(Future.failedFuture(e));
  }

  @Override
  public void onSuccess(ResultType result) {
    handleSuccess(result);
  }

  public abstract void handleSuccess(ResultType result);
}
