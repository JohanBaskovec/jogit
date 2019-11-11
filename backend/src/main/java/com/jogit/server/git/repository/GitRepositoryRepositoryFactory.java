package com.jogit.server.git.repository;

import io.vertx.sqlclient.Transaction;

public class GitRepositoryRepositoryFactory {
  private final GitRepositoryService gitRepositoryService;

  public GitRepositoryRepositoryFactory(
    GitRepositoryService gitRepositoryService
  ) {
    this.gitRepositoryService = gitRepositoryService;
  }

  GitRepositoryRepository get(Transaction transaction) {
    return new GitRepositoryRepository(transaction, gitRepositoryService);
  }
}
