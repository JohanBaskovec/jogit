package com.example.starter;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

/**
 * Copied from FormLoginHandlerImpl
 */
public class LoginHandler implements Handler<RoutingContext> {
  private final AuthProvider authProvider;


  public LoginHandler(AuthProvider authProvider) {
    this.authProvider = authProvider;
  }

  @Override
  public void handle(RoutingContext context) {
    HttpServerRequest req = context.request();
    if (req.method() != HttpMethod.POST) {
      context.fail(405); // Must be a POST
    } else {
      JsonObject body = context.getBodyAsJson();
      String username = body.getString("username");
      String password = body.getString("password");
      if (username == null || password == null) {
        System.out.println("No username or password provided in form - did you forget to include a BodyHandler?");
        context.fail(400);
      } else {
        Session session = context.session();
        authProvider.authenticate(body, res -> {
          if (res.succeeded()) {
            User user = res.result();
            context.setUser(user);
            if (session != null) {
              // the user has upgraded from unauthenticated to authenticated
              // session should be upgraded as recommended by owasp
              session.regenerateId();
            }
            //TODO: respond with something...
            req.response().end();
          } else {
            context.fail(403);  // Failed login
          }
        });
      }
    }
  }
}
