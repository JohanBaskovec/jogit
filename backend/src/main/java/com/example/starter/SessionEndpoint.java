package com.example.starter;

import com.example.starter.gprc.GetCurrentSessionReply;
import com.example.starter.gprc.GetCurrentSessionRequest;
import com.example.starter.gprc.Session;
import com.example.starter.gprc.SessionServiceGrpc;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

class SessionEndpoint extends SessionServiceGrpc.SessionServiceVertxImplBase {
  private final PgPool pgClient;
  private final SessionRepositoryFactory sessionRepositoryFactory;

  SessionEndpoint(
    PgPool pgClient,
    SessionRepositoryFactory sessionRepositoryFactory) {
    this.pgClient = pgClient;
    this.sessionRepositoryFactory = sessionRepositoryFactory;
  }

  @Override
  public void getCurrentSession(GetCurrentSessionRequest request, Future<GetCurrentSessionReply> response) {
    pgClient.begin(new ErrorHandler<Transaction, GetCurrentSessionReply>(response) {
      @Override
      public void handleSuccess(Transaction transaction) {
        SessionRepository sessionRepository = sessionRepositoryFactory.get(transaction);
        sessionRepository.getSessionById(request.getSessionToken(), new ErrorHandlerWithTransaction<Session, GetCurrentSessionReply>(response, transaction) {
          @Override
          public void handleSuccess(Session session) {
            GetCurrentSessionReply.Builder builder = GetCurrentSessionReply.newBuilder();
            if (session != null) {
              builder.setSession(session);
            }
            response.complete(builder.buildPartial());
          }
        });
      }
    });
  }
}

