package com.jogit.server.security.user;

import com.jogit.server.linux.LinuxService;
import io.vertx.sqlclient.Transaction;

public class UserRepositoryFactory {
  private final UserService userService;
  private final LinuxService linuxService;

  public UserRepositoryFactory(UserService userService, LinuxService linuxService) {
    this.userService = userService;
    this.linuxService = linuxService;
  }

  public UserRepository get(Transaction transaction) {
    return new UserRepository(transaction, userService, linuxService);
  }
}
