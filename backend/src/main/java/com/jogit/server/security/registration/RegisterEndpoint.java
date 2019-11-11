package com.jogit.server.security.registration;

import com.jogit.server.gprc.RegisterGrpc;
import com.jogit.server.gprc.RegisterReply;
import com.jogit.server.gprc.RegisterRequest;
import com.jogit.server.gprc.User;
import com.jogit.server.security.login.AuthenticationService;
import com.jogit.server.security.user.UserRepository;
import com.jogit.server.security.user.UserRepositoryFactory;
import com.jogit.server.validation.ObjectValidationResult;
import com.jogit.server.validation.ObjectValidator;
import com.jogit.server.vertx.ErrorHandler;
import com.jogit.server.vertx.RequestContext;
import io.grpc.Status;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

public class RegisterEndpoint extends RegisterGrpc.RegisterVertxImplBase {
  private final PgPool pgClient;
  private final UserRepositoryFactory userRepositoryFactory;
  private final AuthenticationService authService;
  private final ObjectValidator requestValidator;

  public RegisterEndpoint(
    PgPool pgClient,
    UserRepositoryFactory userRepositoryFactory,
    AuthenticationService authService,
    ObjectValidator requestValidator) {
    this.pgClient = pgClient;
    this.userRepositoryFactory = userRepositoryFactory;
    this.authService = authService;
    this.requestValidator = requestValidator;
  }

  @Override
  public void register(RegisterRequest request, Future<RegisterReply> future) {
    RequestContext<RegisterRequest, RegisterReply> requestContext = new RequestContext<>(pgClient, request, future);
    requestContext.run(() -> {
      ObjectValidationResult objectValidationResult = requestValidator.validate(request);
      if (objectValidationResult.isInvalid()) {
        future.fail(Status.INVALID_ARGUMENT.asRuntimeException());
        return;
      }
      requestContext.begin((Transaction transaction) -> {
        String salt = authService.generateSalt();
        String passwordHash = authService.computeHash(request.getPassword(), salt);
        User user = User.newBuilder()
          .setUsername(request.getUsername())
          .setPassword(passwordHash)
          .setPasswordSalt(salt)
          .build();
        UserRepository userRepository = userRepositoryFactory.get(transaction);
        userRepository.insert(user, new ErrorHandler<Void, RegisterReply>(requestContext) {
          @Override
          public void handleSuccess(Void result) {
            requestContext.handle(Future.succeededFuture(RegisterReply.newBuilder().build()));
          }
        });
      });
    });
  }
}
