package com.example.starter;

import com.example.starter.gprc.RegisterGrpc;
import com.example.starter.gprc.RegisterReply;
import com.example.starter.gprc.RegisterRequest;
import com.example.starter.gprc.User;
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
        userRepository.insert(user, new ErrorHandlerWithTransaction<Void, RegisterReply>(future, transaction) {
          @Override
          public void handleSuccess(Void result) {
            future.complete(RegisterReply.newBuilder().build());
            transaction.commit();
          }
        });
      }
    });
  }
}
