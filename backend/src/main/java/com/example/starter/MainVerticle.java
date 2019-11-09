package com.example.starter;

import com.example.starter.validation.ObjectValidator;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
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
    PRNG prng = new PRNG(vertx);
    RandomService randomService = new RandomService();
    JsonObject config = vertx.getOrCreateContext().config();
    if (config == null) {
      String confFilePath = System.getenv("JOGIT_CONF");
      config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
    }
    DatabaseService databaseService = new DatabaseService();
    String rootPassword = config.getString("rootPassword");
    ProcessExecutorAsRoot processExecutorAsRoot = new ProcessExecutorAsRootImpl(rootPassword);
    fileSystemService = new FileSystemServiceImpl(processExecutorAsRoot);
    GitRepositoryService gitRepositoryService = new GitRepositoryService(
      fileSystemService,
      processExecutorAsRoot
    );
    PgPool pgClient = databaseService.newPgPool(vertx, config.getJsonObject("database"));

    UserService userService = new UserService();
    SessionService sessionService = new SessionService(userService, prng);
    SessionRepositoryFactory sessionRepositoryFactory = new SessionRepositoryFactory(
      sessionService
    );
    AuthenticationService authService = new AuthenticationServiceImpl(vertx);

    LinuxService linuxService = new LinuxService(processExecutorAsRoot);
    UserRepositoryFactory userRepositoryFactory = new UserRepositoryFactory(userService, linuxService);

    LoginEndpoint loginEndpoint = new LoginEndpoint(
      pgClient,
      userRepositoryFactory,
      sessionRepositoryFactory,
      authService,
      sessionService
    );
    SessionEndpoint sessionEndpoint = new SessionEndpoint(pgClient, sessionRepositoryFactory);

    Integer port = config.getJsonObject("server").getInteger("port");
    if (port == null) {
      startPromise.fail("port must not be null.");
      return;
    }
    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx, "localhost", port)
      .addService(newRegisterEndpoint(pgClient, authService, userRepositoryFactory))
      .addService(loginEndpoint)
      .addService(sessionEndpoint)
      .build();

    rpcServer.start(
      (AsyncResult<Void> rpcServerStart) -> {
        if (rpcServerStart.succeeded()) {
          startPromise.complete();
          System.out.println("Server started on port " + port);
        } else {
          startPromise.fail(rpcServerStart.cause());
        }
      });
  }

  private RegisterEndpoint newRegisterEndpoint(
    PgPool pgClient,
    AuthenticationService authService,
    UserRepositoryFactory userRepositoryFactory
  ) throws IOException {
    String registrationRequestValidationConstraints = StaticFileSystemService.readResourceToString("validation/registration.json");
    ObjectValidator registerRequestValidator = new ObjectValidator(new JsonObject(registrationRequestValidationConstraints));
    return new RegisterEndpoint(
      pgClient,
      userRepositoryFactory,
      authService,
      registerRequestValidator
    );
  }
}
