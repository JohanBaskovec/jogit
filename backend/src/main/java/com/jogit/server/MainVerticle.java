package com.jogit.server;

import com.jogit.server.database.DatabaseService;
import com.jogit.server.fs.FileSystemService;
import com.jogit.server.fs.FileSystemServiceImpl;
import com.jogit.server.fs.StaticFileSystemService;
import com.jogit.server.git.repository.GitRepositoryEndpoint;
import com.jogit.server.git.repository.GitRepositoryRepositoryFactory;
import com.jogit.server.git.repository.GitRepositoryService;
import com.jogit.server.linux.LinuxService;
import com.jogit.server.linux.ProcessExecutorAsRoot;
import com.jogit.server.linux.ProcessExecutorAsRootImpl;
import com.jogit.server.security.login.AuthenticationService;
import com.jogit.server.security.login.AuthenticationServiceImpl;
import com.jogit.server.security.login.LoginEndpoint;
import com.jogit.server.security.registration.RegisterEndpoint;
import com.jogit.server.security.session.SessionEndpoint;
import com.jogit.server.security.session.SessionRepositoryFactory;
import com.jogit.server.security.session.SessionService;
import com.jogit.server.security.user.UserRepositoryFactory;
import com.jogit.server.security.user.UserService;
import com.jogit.server.validation.ObjectValidator;
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
    JsonObject schema = new JsonObject(StaticFileSystemService.readResourceToString("validation/build/schema.json"));

    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx, "localhost", port)
      .addService(newRegisterEndpoint(
        pgClient,
        authService,
        userRepositoryFactory,
        schema.getJsonObject("requests")
      ))
      .addService(newLoginEndpoint(
        pgClient,
        sessionService,
        sessionRepositoryFactory,
        authService,
        userRepositoryFactory,
        schema.getJsonObject("requests")
      ))
      .addService(sessionEndpoint)
      .addService(newGitRepositoryEndpoint(
        pgClient,
        sessionRepositoryFactory,
        gitRepositoryRepositoryFactory,
        gitRepositoryService,
        schema.getJsonObject("requests")
      ))
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

  private LoginEndpoint newLoginEndpoint(PgPool pgClient, SessionService sessionService, SessionRepositoryFactory sessionRepositoryFactory, AuthenticationService authService, UserRepositoryFactory userRepositoryFactory, JsonObject schema) throws IOException {
    ObjectValidator requestValidator = new ObjectValidator(schema.getJsonObject("LoginRequest"));
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
    UserRepositoryFactory userRepositoryFactory,
    JsonObject schema) throws IOException {
    ObjectValidator requestValidator = new ObjectValidator(schema.getJsonObject("RegisterRequest"));
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
    GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory,
    GitRepositoryService gitRepositoryService,
    JsonObject schema
  ) throws IOException {
    // TODO: reuse constraints for fields that are common to multiple requests
    // for example, reuse user's username in login, register, get user's git repositories
    ObjectValidator createGitRepositoryRequestValidator = new ObjectValidator(schema.getJsonObject("CreateGitRepositoryRequest"));
    ObjectValidator getGitRepositoryByUserRequestValidator = new ObjectValidator(schema.getJsonObject("GetGitRepositoryOfUserRequest"));
    ObjectValidator getGitRepositoryDirectoryRequestValidator = new ObjectValidator(schema.getJsonObject("GetGitRepositoryDirectoryRequest"));

    return new GitRepositoryEndpoint(
      pgClient,
      gitRepositoryRepositoryFactory,
      sessionRepositoryFactory,
      createGitRepositoryRequestValidator,
      getGitRepositoryByUserRequestValidator,
      getGitRepositoryDirectoryRequestValidator,
      gitRepositoryService
    );
  }

}
