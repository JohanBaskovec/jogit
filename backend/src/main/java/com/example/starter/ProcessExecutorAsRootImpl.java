package com.example.starter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ProcessExecutorAsRootImpl implements ProcessExecutorAsRoot {
  private final String rootPassword;

  public ProcessExecutorAsRootImpl(String rootPassword) {
    this.rootPassword = rootPassword;
  }


  /**
   * Execute a process as root. processBuilder.command() must
   * have been called.
   *
   * @param processBuilder
   */
  public void execute(ProcessBuilder processBuilder) {
    try {
      ArrayList<String> commandWords = new ArrayList<>();
      commandWords.add("sudo -S");
      commandWords.addAll(processBuilder.command());
      processBuilder.command(commandWords);

      processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT);
      Process process = processBuilder.start();
      OutputStream os = process.getOutputStream();
      os.write(rootPassword.getBytes());
      os.write("\n".getBytes());
      os.flush();
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
