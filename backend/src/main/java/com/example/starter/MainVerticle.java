package com.example.starter;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PRNG;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import io.vertx.pgclient.PgPool;

import java.io.IOException;

public class MainVerticle extends AbstractVerticle {
  private FileSystemService fileSystemService;

  @Override
  public void start(Promise<Void> startPromise) throws IOException {
    fileSystemService = new FileSystemService();
    PRNG prng = new PRNG(vertx);
    RandomService randomService = new RandomService();
    String confFilePath = System.getenv("JOGIT_CONF");
    JsonObject config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
    DatabaseService databaseService = new DatabaseService();
    String rootPassword = config.getString("rootPassword");
    GitRepositoryService gitRepositoryService = new GitRepositoryService(rootPassword);
    PgPool pgClient = databaseService.newPgPool(vertx, config.getJsonObject("database"));

    UserService userService = new UserService();
    SessionService sessionService = new SessionService(userService, prng);
    SessionRepositoryFactory sessionRepositoryFactory = new SessionRepositoryFactory(
      sessionService
    );
    AuthenticationService authService = new AuthenticationServiceImpl(vertx);

    UserRepositoryFactory userRepositoryFactory = new UserRepositoryFactory(userService);
    RegisterEndpoint registerEndpoint = new RegisterEndpoint(pgClient, userRepositoryFactory, authService);
    LoginEndpoint loginEndpoint = new LoginEndpoint(
      pgClient,
      userRepositoryFactory,
      sessionRepositoryFactory,
      authService,
      sessionService
    );

    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx, "localhost", 8888)
      .addService(registerEndpoint)
      .addService(loginEndpoint)
      .build();

    rpcServer.start(
      (AsyncResult<Void> rpcServerStart) -> {
        if (rpcServerStart.succeeded()) {
          startPromise.complete();
          System.out.println("Server started on port 8888");
        } else {
          startPromise.fail(rpcServerStart.cause());
        }
      });
  }
}
