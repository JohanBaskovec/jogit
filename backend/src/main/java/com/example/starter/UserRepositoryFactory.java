package com.example.starter;

import io.vertx.sqlclient.Transaction;

public class UserRepositoryFactory {
  private final UserService userService;

  UserRepositoryFactory(UserService userService) {
    this.userService = userService;
  }

  UserRepository get(Transaction transaction) {
    return new UserRepository(transaction, userService);
  }
}
