package com.example.starter;

import com.example.starter.gprc.LoginGrpc;
import com.example.starter.gprc.LoginReply;
import com.example.starter.gprc.LoginRequest;
import com.example.starter.gprc.Session;
import com.example.starter.gprc.User;
import io.grpc.Status;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
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
    pgClient.begin(new ErrorHandler<Transaction, LoginReply>(future) {
      @Override
      public void handleSuccess(Transaction transaction) {
        authService.authenticate(
          userRepositoryFactory.get(transaction),
          request,
          new AuthenticationHandler(
            handler,
            transaction,
            sessionRepositoryFactory,
            sessionService
          )
        );
      }
    });
  }

  private static class AuthenticationHandler extends ErrorHandlerWithTransaction<User, LoginReply> {
    private final SessionRepositoryFactory sessionRepositoryFactory;
    private final SessionService sessionService;

    AuthenticationHandler(
      Handler<AsyncResult<LoginReply>> handler,
      Transaction transaction,
      SessionRepositoryFactory sessionRepositoryFactory,
      SessionService sessionService
    ) {
      super(handler, transaction);
      this.sessionRepositoryFactory = sessionRepositoryFactory;
      this.sessionService = sessionService;
    }

    @Override
    public void handleSuccess(User user) {
      if (user == null) {
        handler.handle(Future.failedFuture(Status.UNAUTHENTICATED.withDescription("Invalid username or password").asRuntimeException()));
        return;
      }

      SessionRepository sessionRepository = sessionRepositoryFactory.get(transaction);
      Session session = Session.newBuilder()
        .setId(sessionService.generateSessionId())
        .setUser(user)
        .setUserUsername(user.getUsername())
        .build();
      sessionRepository.insert(session, new InsertSessionHandler(handler, transaction, session));
    }
  }

  private static class InsertSessionHandler extends ErrorHandlerWithTransaction<Void, LoginReply> {
    private final Session session;

    InsertSessionHandler(Handler<AsyncResult<LoginReply>> future, Transaction transaction, Session session) {
      super(future, transaction);
      this.session = session;
    }

    @Override
    public void handleSuccess(Void result) {
      transaction.commit();
      // TODO: find a way to put the session token in a httponly cookie
      // TODO: do not return user's password and salt!
      handler.handle(Future.succeededFuture(LoginReply.newBuilder().setSession(session).build()));
    }
  }
}
