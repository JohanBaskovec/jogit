package com.example.starter;

import com.example.starter.gprc.RegisterGrpc;
import com.example.starter.gprc.RegisterReply;
import com.example.starter.gprc.RegisterRequest;
import com.example.starter.gprc.User;
import com.google.common.base.Utf8;
import io.grpc.Status;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

class RegisterEndpoint extends RegisterGrpc.RegisterVertxImplBase {
  private final PgPool pgClient;
  private final UserRepositoryFactory userRepositoryFactory;
  private final AuthenticationService authService;

  RegisterEndpoint(
    PgPool pgClient,
    UserRepositoryFactory userRepositoryFactory,
    AuthenticationService authService) {
    this.pgClient = pgClient;
    this.userRepositoryFactory = userRepositoryFactory;
    this.authService = authService;
  }

  @Override
  public void register(RegisterRequest request, Future<RegisterReply> future) {
    RequestContext<RegisterRequest, RegisterReply> requestContext = new RequestContext<>(pgClient, request, future);
    requestContext.run(() -> {
      int userNameUtf8Length = Utf8.encodedLength(request.getUsername());
      if (userNameUtf8Length > 32) {
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
        userRepository.insert(user, new WithRequestContextHandler<RegisterRequest, RegisterReply, Void>(requestContext) {
          @Override
          public void handleSuccess(Void result) {
            requestContext.handle(Future.succeededFuture(RegisterReply.newBuilder().build()));
            transaction.commit();
          }
        });
      });
    });
  }
}
