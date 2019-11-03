package com.example.starter;

import com.example.starter.gprc.LoginGrpc;
import com.example.starter.gprc.LoginReply;
import com.example.starter.gprc.LoginRequest;
import com.example.starter.gprc.Session;
import com.example.starter.gprc.User;
import io.grpc.Status;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

class LoginEndpoint extends LoginGrpc.LoginVertxImplBase {
  private final PgPool pgClient;
  private final UserRepositoryFactory userRepositoryFactory;
  private final SessionRepositoryFactory sessionRepositoryFactory;
  private final AuthenticationService authService;
  private final SessionService sessionService;

  LoginEndpoint(
    PgPool pgClient,
    UserRepositoryFactory userRepositoryFactory,
    SessionRepositoryFactory sessionRepositoryFactory,
    AuthenticationService authService,
    SessionService sessionService
  ) {
    this.pgClient = pgClient;
    this.userRepositoryFactory = userRepositoryFactory;
    this.sessionRepositoryFactory = sessionRepositoryFactory;
    this.authService = authService;
    this.sessionService = sessionService;
  }

  @Override
  public void login(LoginRequest request, Future<LoginReply> future) {
    pgClient.begin((AsyncResult<Transaction> maybeTransaction) -> {
      if (maybeTransaction.failed()) {
        System.out.println("pgClient.begin() failed.");
        maybeTransaction.cause().printStackTrace();
        future.fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
      } else {
        onTransactionInitialized(request, future, maybeTransaction.result());
      }
    });
  }

  private void onTransactionInitialized(
    LoginRequest request,
    Future<LoginReply> future,
    Transaction transaction
  ) {
    authService.authenticate(userRepositoryFactory.get(transaction), request, (AsyncResult<User> userResult) -> {
      if (userResult.failed()) {
        System.out.println("userRepositoryFactory.get failed");
        future.fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
        transaction.rollback();
        return;
      }
      User user = userResult.result();
      SessionRepository sessionRepository = sessionRepositoryFactory.get(transaction);
      Session session = Session.newBuilder()
        .setId(sessionService.generateSessionId())
        .setUser(user)
        .setUserUsername(user.getUsername())
        .build();
      sessionRepository.insert(session, (AsyncResult<Void> insertResult) -> {
        if (insertResult.failed()) {
          future.fail(Status.INTERNAL.withDescription("Internal error.").asRuntimeException());
          transaction.rollback();
          return;
        }
        transaction.commit();
        // TODO: find a way to put the session token in a httponly cookie
        future.complete(LoginReply.newBuilder().setSessionToken(session.getId()).build());

      });

    });
  }
}
