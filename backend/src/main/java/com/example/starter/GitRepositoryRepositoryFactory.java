package com.example.starter;

import io.vertx.sqlclient.Transaction;

public class GitRepositoryRepositoryFactory {
  private final GitRepositoryService gitRepositoryService;

  GitRepositoryRepositoryFactory(
    GitRepositoryService gitRepositoryService
  ) {
    this.gitRepositoryService = gitRepositoryService;
  }

  GitRepositoryRepository get(Transaction transaction) {
    return new GitRepositoryRepository(transaction, gitRepositoryService);
  }
}
