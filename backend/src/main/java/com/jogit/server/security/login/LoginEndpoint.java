package com.jogit.server.security.login;

import com.jogit.server.vertx.RequestContext;
import com.jogit.server.security.user.UserRepositoryFactory;
import com.jogit.server.grpc.LoginGrpc;
import com.jogit.server.grpc.LoginReply;
import com.jogit.server.grpc.LoginRequest;
import com.jogit.server.grpc.Session;
import com.jogit.server.grpc.User;
import com.jogit.server.security.session.SessionRepository;
import com.jogit.server.security.session.SessionRepositoryFactory;
import com.jogit.server.security.session.SessionService;
import com.jogit.server.validation.ObjectValidationResult;
import com.jogit.server.validation.ObjectValidator;
import com.jogit.server.vertx.ErrorHandler;
import io.grpc.Status;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

public class LoginEndpoint extends LoginGrpc.LoginVertxImplBase {
  private final PgPool pgClient;
  private final UserRepositoryFactory userRepositoryFactory;
  private final SessionRepositoryFactory sessionRepositoryFactory;
  private final AuthenticationService authService;
  private final SessionService sessionService;
  private final ObjectValidator requestValidator;

  public LoginEndpoint(
    PgPool pgClient,
    UserRepositoryFactory userRepositoryFactory,
    SessionRepositoryFactory sessionRepositoryFactory,
    AuthenticationService authService,
    SessionService sessionService,
    ObjectValidator requestValidator
  ) {
    this.pgClient = pgClient;
    this.userRepositoryFactory = userRepositoryFactory;
    this.sessionRepositoryFactory = sessionRepositoryFactory;
    this.authService = authService;
    this.sessionService = sessionService;
    this.requestValidator = requestValidator;
  }

  @Override
  public void login(LoginRequest request, Future<LoginReply> future) {
    RequestContext<LoginRequest, LoginReply> requestContext = new RequestContext<>(pgClient, request, future);
    requestContext.run(() -> {
      ObjectValidationResult objectValidationResult = requestValidator.validate(request);
      if (objectValidationResult.isInvalid()) {
        future.fail(Status.INVALID_ARGUMENT.asRuntimeException());
        return;
      }
      requestContext.begin((Transaction transaction) -> {
        authService.authenticate(
          userRepositoryFactory.get(transaction),
          request,
          new AuthenticationHandler(
            requestContext,
            transaction,
            sessionRepositoryFactory,
            sessionService
          )
        );
      });
    });
  }

  private static class AuthenticationHandler extends ErrorHandler<User, LoginReply> {
    private final Transaction transaction;
    private final SessionRepositoryFactory sessionRepositoryFactory;
    private final SessionService sessionService;

    AuthenticationHandler(
      RequestContext<LoginRequest, LoginReply> requestContext,
      Transaction transaction,
      SessionRepositoryFactory sessionRepositoryFactory,
      SessionService sessionService
    ) {
      super(requestContext);
      this.transaction = transaction;
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
      // TODO: do not insert session with same user
      sessionRepository.insert(session, new InsertSessionHandler(handler, session));
    }
  }

  private static class InsertSessionHandler extends ErrorHandler<Void, LoginReply> {
    private final Session session;

    InsertSessionHandler(Handler<AsyncResult<LoginReply>> handler, Session session) {
      super(handler);
      this.session = session;
    }

    @Override
    public void handleSuccess(Void result) {
      // TODO: find a way to put the session token in a httponly cookie
      // TODO: do not return user's password and salt!
      handler.handle(Future.succeededFuture(LoginReply.newBuilder().setSession(session).build()));
    }
  }
}
