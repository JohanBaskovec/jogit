package com.example.starter;

import io.vertx.core.Future;
import io.vertx.sqlclient.Transaction;

public abstract class ErrorHandlerWithTransaction<T, U> extends ErrorHandler<T, U> {
  protected final Transaction transaction;

  ErrorHandlerWithTransaction(Future<U> future, Transaction transaction) {
    super(future);
    this.transaction = transaction;
  }

  @Override
  public void onFailure(Throwable e) {
    super.onFailure(e);
    transaction.rollback();
  }

  public abstract void handleSuccess(T result);
}
