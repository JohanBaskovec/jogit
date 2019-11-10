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
    String confFilePath = System.getenv("JOGIT_CONF");
    JsonObject config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
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

    SessionEndpoint sessionEndpoint = new SessionEndpoint(pgClient, sessionRepositoryFactory);

    GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory = new GitRepositoryRepositoryFactory(gitRepositoryService);
    Integer port = config.getJsonObject("server").getInteger("port");
    if (port == null) {
      startPromise.fail("port must not be null.");
      return;
    }
    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx, "localhost", port)
      .addService(newRegisterEndpoint(pgClient, authService, userRepositoryFactory))
      .addService(newLoginEndpoint(pgClient, sessionService, sessionRepositoryFactory, authService, userRepositoryFactory))
      .addService(sessionEndpoint)
      .addService(newGitRepositoryEndpoint(pgClient, sessionRepositoryFactory, gitRepositoryRepositoryFactory))
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

  private LoginEndpoint newLoginEndpoint(PgPool pgClient, SessionService sessionService, SessionRepositoryFactory sessionRepositoryFactory, AuthenticationService authService, UserRepositoryFactory userRepositoryFactory) throws IOException {
    String requestConstraints = StaticFileSystemService.readResourceToString("validation/login.json");
    ObjectValidator requestValidator = new ObjectValidator(new JsonObject(requestConstraints));
    return new LoginEndpoint(
      pgClient,
      userRepositoryFactory,
      sessionRepositoryFactory,
      authService,
      sessionService,
      requestValidator
    );
  }

  private RegisterEndpoint newRegisterEndpoint(
    PgPool pgClient,
    AuthenticationService authService,
    UserRepositoryFactory userRepositoryFactory
  ) throws IOException {
    String requestConstraints = StaticFileSystemService.readResourceToString("validation/registration.json");
    ObjectValidator requestValidator = new ObjectValidator(new JsonObject(requestConstraints));
    return new RegisterEndpoint(
      pgClient,
      userRepositoryFactory,
      authService,
      requestValidator
    );
  }

  private GitRepositoryEndpoint newGitRepositoryEndpoint(
    PgPool pgClient,
    SessionRepositoryFactory sessionRepositoryFactory,
    GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory
  ) throws IOException {
    // TODO: reuse constraints for fields that are common to multiple requests
    // for example, reuse user's username in login, register, get user's git repositories
    String createGitRepositoryConstraints = StaticFileSystemService.readResourceToString("validation/create_git_repository.json");
    ObjectValidator createGitRepositoryRequestValidator = new ObjectValidator(new JsonObject(createGitRepositoryConstraints));
    String getGitRepositoryRequestConstraints = StaticFileSystemService.readResourceToString("validation/get_user_git_repositories.json");
    ObjectValidator getGitRepositoryByUserRequestValidator = new ObjectValidator(new JsonObject(getGitRepositoryRequestConstraints));
    return new GitRepositoryEndpoint(
      pgClient,
      gitRepositoryRepositoryFactory,
      sessionRepositoryFactory,
      createGitRepositoryRequestValidator,
      getGitRepositoryByUserRequestValidator
    );
  }

}
