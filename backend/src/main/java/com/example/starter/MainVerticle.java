package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.impl.AuthHandlerImpl;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Tuple;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();
    RandomService randomService = new RandomService();
    String rootPassword = Vertx.currentContext().config().getString("rootPassword");
    RepositoryService repositoryService = new RepositoryService(rootPassword);
    Router router = Router.router(vertx);

    // TODO: move config to secret config file
    PgConnectOptions pgConnectOptions = new PgConnectOptions()
      .setPort(8092)
      .setHost("localhost")
      .setDatabase("jogitdev")
      .setUser("postgres")
      .setPassword("c4ef37c0fbd747da1c63c0f87d7c62df");
    PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
    PgPool pgClient = PgPool.pool(vertx, pgConnectOptions, poolOptions);
    PgAuth pgAuthProvider = PgAuth.create(vertx, pgClient);
    BodyHandler bodyHandler = BodyHandler.create();
    SessionStore sessionStore = LocalSessionStore.create(vertx);
    SessionHandler sessionHandler = SessionHandler.create(sessionStore);
    sessionHandler.setAuthProvider(pgAuthProvider);
    LoginHandler loginHandler = new LoginHandler(pgAuthProvider);
    AppAuthHandler authHandler = new AppAuthHandler();
    router.get("/hello")
      .handler(sessionHandler)
      .handler(authHandler)
      .handler(new Handler<RoutingContext>() {
      @Override
      public void handle(RoutingContext rc) {
        rc.json(new JsonObject().put("hello", "world"));
      }
    });
    router.post("/login")
      .handler(sessionHandler)
      .handler(bodyHandler)
      .handler(loginHandler);

    router.post("/register")
      .handler(sessionHandler)
      .handler(bodyHandler)
      .handler(new Handler<RoutingContext>() {
        // TODO: move to its own class
        @Override
        public void handle(RoutingContext rc) {
          JsonObject body = rc.getBodyAsJson();
          //TODO: validation
          String password = body.getString("password");
          String username = body.getString("username");

          String salt = pgAuthProvider.generateSalt();
          String hash = pgAuthProvider.computeHash(password, salt);
          //TODO: create Linux user too
          pgClient.preparedQuery("insert into \"user\" values($1, $2, $3)", Tuple.of(username, hash, salt), queryRes -> {
            if (queryRes.succeeded()) {
              // TODO: logging
              System.out.println("User " + username + " registered.");
              rc.response().setStatusCode(201).end();
            } else {
              // TODO: error response object
              rc.response().setStatusCode(500).end();
            }
          });
        }
      });
    router.post("/repository")
      .handler(sessionHandler)
      .handler(authHandler)
      .handler(bodyHandler)
      .handler(
        new CreateRepositoryHandler(randomService, repositoryService)
      );

    server.requestHandler(router).listen(8888, (AsyncResult<HttpServer> http) -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
