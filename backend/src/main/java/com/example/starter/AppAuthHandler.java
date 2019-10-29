package com.example.starter;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class AppAuthHandler implements Handler<RoutingContext> {
  // TODO: copy part of AuthHandlerImpl
  public AppAuthHandler() {
  }

  public void handle(RoutingContext rc) {
    if (rc.user() == null) {
      rc.response().setStatusCode(403).end();
    } else {
      rc.next();
    }
  }
}
