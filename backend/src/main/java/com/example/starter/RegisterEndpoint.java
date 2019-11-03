package com.example.starter;

import com.example.starter.gprc.RegisterGrpc;
import com.example.starter.gprc.RegisterReply;
import com.example.starter.gprc.RegisterRequest;
import com.example.starter.gprc.User;
import io.grpc.Status;
import io.vertx.core.AsyncResult;
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
    pgClient.begin((AsyncResult<Transaction> maybeTransaction) -> {
      // TODO: make a wrapper around Handler that catch all errors and
      // return the error to the client
      try {
        if (maybeTransaction.failed()) {
          System.out.println("pgClient.begin() failed.");
          maybeTransaction.cause().printStackTrace();
          future.fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
        } else {
          onTransactionInitialized(request, future, maybeTransaction.result());
        }
      } catch (Throwable e) {
        e.printStackTrace();
        future.fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
      }
    });
  }

  private void onTransactionInitialized(
    RegisterRequest request,
    Future<RegisterReply> future,
    Transaction transaction
  ) {
    String salt = authService.generateSalt();
    String passwordHash = authService.computeHash(request.getPassword(), salt);
    User user = User.newBuilder()
      .setUsername(request.getUsername())
      .setPassword(passwordHash)
      .setPasswordSalt(salt)
      .build();
    UserRepository userRepository = userRepositoryFactory.get(transaction);
    userRepository.insert(user, (AsyncResult<Void> result) -> {
      if (result.failed()) {
        future.fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
        transaction.rollback();
      } else {
        future.complete(RegisterReply.newBuilder().build());
        transaction.commit();
      }
    });
  }
}
