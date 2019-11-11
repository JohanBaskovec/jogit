package com.jogit.server.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public interface RequestContextHandler<T> {
  public void handle(Handler<AsyncResult<T>> handler);
}
