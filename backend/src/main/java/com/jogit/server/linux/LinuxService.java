package com.jogit.server.linux;

import com.jogit.server.gprc.User;

import java.util.ArrayList;
import java.util.List;

public class LinuxService {
  private final ProcessExecutorAsRoot processExecutorAsRoot;

  public LinuxService(
    ProcessExecutorAsRoot processExecutorAsRoot
  ) {
    this.processExecutorAsRoot = processExecutorAsRoot;
  }

  public void createUserAccount(User user) {
    ProcessBuilder accountCreateProcess = new ProcessBuilder()
      .command("useradd", user.getUsername());
    this.processExecutorAsRoot.execute(accountCreateProcess);
    ProcessBuilder passwordChangeProcess = new ProcessBuilder()
      .command("passwd", user.getUsername());
    List<String> passwords = new ArrayList<>();
    passwords.add(user.getPassword());
    passwords.add(user.getPassword());
    this.processExecutorAsRoot.execute(passwordChangeProcess, passwords);
  }

  public void deleteUserAccount(String username) {
    ProcessBuilder accountDeletionProcess = new ProcessBuilder()
      .command("userdel", "-r", username);
    this.processExecutorAsRoot.execute(accountDeletionProcess);
  }
}
