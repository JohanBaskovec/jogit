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
    int userNameUtf8Length = Utf8.encodedLength(request.getUsername());
    if (userNameUtf8Length > 32) {
      future.fail(Status.INVALID_ARGUMENT.asRuntimeException());
      return;
    }
    pgClient.begin(new ErrorHandler<Transaction, RegisterReply>(future) {
      @Override
      public void handleSuccess(Transaction transaction) {
        String salt = authService.generateSalt();
        String passwordHash = authService.computeHash(request.getPassword(), salt);
        User user = User.newBuilder()
          .setUsername(request.getUsername())
          .setPassword(passwordHash)
          .setPasswordSalt(salt)
          .build();
        UserRepository userRepository = userRepositoryFactory.get(transaction);
        userRepository.insert(user, new ErrorHandlerWithTransaction<Void, RegisterReply>(handler, transaction) {
          @Override
          public void handleSuccess(Void result) {
            handler.handle(Future.succeededFuture(RegisterReply.newBuilder().build()));
            transaction.commit();
          }
        });
      }
    });
  }
}
