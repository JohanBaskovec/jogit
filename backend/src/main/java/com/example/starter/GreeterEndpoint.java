package com.example.starter;

import com.example.starter.gprc.GreeterGrpc;
import com.example.starter.gprc.HelloReply;
import com.example.starter.gprc.HelloRequest;
import com.example.starter.gprc.Session;
import com.google.common.base.Strings;
import io.grpc.Status;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

class GreeterEndpoint extends GreeterGrpc.GreeterVertxImplBase {
  private final PgPool pgClient;
  private final SessionRepositoryFactory sessionRepositoryFactory;

  GreeterEndpoint(PgPool pgClient, SessionRepositoryFactory sessionRepositoryFactory) {
    this.pgClient = pgClient;
    this.sessionRepositoryFactory = sessionRepositoryFactory;
  }

  @Override
  public void sayHello(HelloRequest request, Future<HelloReply> future) {
    if (Strings.isNullOrEmpty(request.getSessionToken())) {
      System.out.println("No session.");
      future.fail(Status.UNAUTHENTICATED.withDescription("No session.").asRuntimeException());
      return;
    }
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
    HelloRequest request,
    Future<HelloReply> future,
    Transaction transaction
  ) {
    SessionRepository sessionRepository = sessionRepositoryFactory.get(transaction);
    sessionRepository.getSessionById(request.getSessionToken(), (AsyncResult<Session> maybeSession) -> {
      if (maybeSession.failed()) {
        future.fail(Status.UNAUTHENTICATED.withDescription("Invalid session token.").asRuntimeException());
      } else {
        Session session = maybeSession.result();
        if (session == null) {
          future.fail(Status.UNAUTHENTICATED.withDescription("Invalid session token.").asRuntimeException());
        } else {
          future.complete(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
        }
      }
    });
  }
}
