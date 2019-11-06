package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.sqlclient.Transaction;

public abstract class ErrorHandlerWithTransaction<T, U> extends ErrorHandler<T, U> {
  protected final Transaction transaction;

  ErrorHandlerWithTransaction(Handler<AsyncResult<U>> handler, Transaction transaction) {
    super(handler);
    this.transaction = transaction;
  }

  @Override
  public void onFailure(Throwable e) {
    super.onFailure(e);
    transaction.rollback();
  }

  public abstract void handleSuccess(T result);
}
