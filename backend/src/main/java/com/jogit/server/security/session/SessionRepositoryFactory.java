package com.jogit.server.security.session;

import io.vertx.sqlclient.Transaction;

public class SessionRepositoryFactory {
  private final SessionService sessionService;

  public SessionRepositoryFactory(
    SessionService sessionService
  ) {
    this.sessionService = sessionService;
  }

  public SessionRepository get(Transaction transaction) {
    return new SessionRepository(transaction, sessionService);
  }
}
