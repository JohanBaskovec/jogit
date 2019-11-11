package com.jogit.server.security.session;

import com.jogit.server.vertx.ErrorHandler;
import com.jogit.server.vertx.RequestContext;
import com.jogit.server.gprc.GetCurrentSessionReply;
import com.jogit.server.gprc.GetCurrentSessionRequest;
import com.jogit.server.gprc.Session;
import com.jogit.server.gprc.SessionServiceGrpc;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

public class SessionEndpoint extends SessionServiceGrpc.SessionServiceVertxImplBase {
  private final PgPool pgClient;
  private final SessionRepositoryFactory sessionRepositoryFactory;

  public SessionEndpoint(
    PgPool pgClient,
    SessionRepositoryFactory sessionRepositoryFactory) {
    this.pgClient = pgClient;
    this.sessionRepositoryFactory = sessionRepositoryFactory;
  }

  @Override
  public void getCurrentSession(GetCurrentSessionRequest request, Future<GetCurrentSessionReply> response) {
    RequestContext<GetCurrentSessionRequest, GetCurrentSessionReply> requestContext = new RequestContext<>(pgClient, request, response);
    requestContext.run(() -> {
      requestContext.begin((Transaction transaction) -> {
        SessionRepository sessionRepository = sessionRepositoryFactory.get(transaction);
        sessionRepository.getSessionById(
          request.getSessionToken(),
          new ErrorHandler<Session, GetCurrentSessionReply>(requestContext) {
            @Override
            public void handleSuccess(Session session) {
              GetCurrentSessionReply.Builder builder = GetCurrentSessionReply.newBuilder();
              if (session != null) {
                builder.setSession(session);
              }
              handler.handle(Future.succeededFuture(builder.buildPartial()));
            }
          });
      });
    });
  }
}

