package com.example.starter;

import io.vertx.sqlclient.Transaction;

public class UserRepositoryFactory {
  private final UserService userService;
  private final LinuxService linuxService;

  UserRepositoryFactory(UserService userService, LinuxService linuxService) {
    this.userService = userService;
    this.linuxService = linuxService;
  }

  UserRepository get(Transaction transaction) {
    return new UserRepository(transaction, userService, linuxService);
  }
}
