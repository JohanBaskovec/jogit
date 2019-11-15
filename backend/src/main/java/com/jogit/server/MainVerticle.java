package com.jogit.server;

import com.jogit.server.database.DatabaseService;
import com.jogit.server.fs.FileSystemService;
import com.jogit.server.fs.FileSystemServiceImpl;
import com.jogit.server.fs.StaticFileSystemService;
import com.jogit.server.git.repository.GitRepositoryEndpoint;
import com.jogit.server.git.repository.GitRepositoryRepositoryFactory;
import com.jogit.server.git.repository.GitRepositoryService;
import com.jogit.server.grpc.*;
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
import com.jogit.server.security.user.UserEndpoint;
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
  private SessionRepositoryFactory sessionRepositoryFactory;
  private UserService userService;
  private SessionService sessionService;
  private LinuxService linuxService;
  private UserRepositoryFactory userRepositoryFactory;
  private AuthenticationService authService;
  private PgPool pgClient;
  private GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory;
  private GitRepositoryService gitRepositoryService;
  private JsonObject requestsSchema;

  @Override
  public void start(Promise<Void> startPromise) throws IOException {
    PRNG prng = new PRNG(vertx);
    String confFilePath = System.getenv("JOGIT_CONF");
    JsonObject config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
    DatabaseService databaseService = new DatabaseService();
    String rootPassword = config.getString("rootPassword");
    ProcessExecutorAsRoot processExecutorAsRoot = new ProcessExecutorAsRootImpl(rootPassword);
    fileSystemService = new FileSystemServiceImpl(processExecutorAsRoot);
    gitRepositoryService = new GitRepositoryService(
      fileSystemService,
      processExecutorAsRoot
    );
    pgClient = databaseService.newPgPool(vertx, config.getJsonObject("database"));

    userService = new UserService();
    sessionService = new SessionService(userService, prng);
    sessionRepositoryFactory = new SessionRepositoryFactory(
      sessionService
    );
    authService = new AuthenticationServiceImpl(vertx);

    linuxService = new LinuxService(processExecutorAsRoot);
    userRepositoryFactory = new UserRepositoryFactory(userService, linuxService);

    SessionEndpoint sessionEndpoint = new SessionEndpoint(pgClient, sessionRepositoryFactory);

    gitRepositoryRepositoryFactory = new GitRepositoryRepositoryFactory(gitRepositoryService);
    Integer port = config.getJsonObject("server").getInteger("port");
    if (port == null) {
      startPromise.fail("port must not be null.");
      return;
    }
    JsonObject schema = new JsonObject(StaticFileSystemService.readResourceToString("validation/build/schema.json"));
    requestsSchema = schema.getJsonObject("requests");

    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx, "localhost", port)
      .addService(newRegisterEndpoint())
      .addService(newLoginEndpoint())
      .addService(sessionEndpoint)
      .addService(newGitRepositoryEndpoint())
      .addService(newUserEndpoint())
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

  private LoginEndpoint newLoginEndpoint() throws IOException {
    ObjectValidator requestValidator = new ObjectValidator(requestsSchema.getJsonObject("LoginRequest"));
    return new LoginEndpoint(
      pgClient,
      userRepositoryFactory,
      sessionRepositoryFactory,
      authService,
      sessionService,
      requestValidator
    );
  }

  private RegisterEndpoint newRegisterEndpoint(){
    ObjectValidator requestValidator = new ObjectValidator(requestsSchema.getJsonObject("RegisterRequest"));
    return new RegisterEndpoint(
      pgClient,
      userRepositoryFactory,
      authService,
      requestValidator
    );
  }

  private GitRepositoryEndpoint newGitRepositoryEndpoint() {
    ObjectValidator createGitRepositoryRequestValidator = new ObjectValidator(requestsSchema.getJsonObject("CreateGitRepositoryRequest"));
    ObjectValidator getGitRepositoryByUserRequestValidator = new ObjectValidator(requestsSchema.getJsonObject("GetGitRepositoryOfUserRequest"));
    ObjectValidator getGitRepositoryDirectoryRequestValidator = new ObjectValidator(requestsSchema.getJsonObject("GetGitRepositoryDirectoryRequest"));

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

  private UserEndpoint newUserEndpoint() {
    return new UserEndpoint(
      pgClient,
      gitRepositoryRepositoryFactory,
      sessionRepositoryFactory,
      this.createObjectValidator(UpdateSshPublicKeyRequest.class),
      this.createObjectValidator(DeleteSshPublicKeyRequest.class),
      this.createObjectValidator(AddSshPublicKeyRequest.class),
      this.createObjectValidator(GetSshPublicKeyRequest.class),
      userRepositoryFactory,
      userService
    );
  }

  private <T> ObjectValidator<T> createObjectValidator(Class<T> clazz) {
    return new ObjectValidator(requestsSchema.getJsonObject(clazz.getSimpleName()));
  }

}
