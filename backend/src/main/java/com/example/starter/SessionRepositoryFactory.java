package com.example.starter;

import io.vertx.sqlclient.Transaction;

public class SessionRepositoryFactory {
  private final SessionService sessionService;

  SessionRepositoryFactory(
    SessionService sessionService
  ) {
    this.sessionService = sessionService;
  }

  SessionRepository get(Transaction transaction) {
    return new SessionRepository(transaction, sessionService);
  }
}
