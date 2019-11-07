package com.example.starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
    execute(processBuilder, null);
  }

  public void execute(ProcessBuilder processBuilder, List<String> inputStrings) {
    try {
      ArrayList<String> commandWords = new ArrayList<>();
      // -k forces the user to type their password even if they did it recently
      commandWords.add("sudo");
      commandWords.add("-kS");
      commandWords.addAll(processBuilder.command());
      processBuilder.command(commandWords);

      processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT);
      Process process = processBuilder.redirectErrorStream(true).start();

      OutputStream os = process.getOutputStream();
      os.write(rootPassword.getBytes());
      os.write("\n".getBytes());
      if (inputStrings != null) {
        for (String string : inputStrings) {
          os.write(string.getBytes());
          os.write("\n".getBytes());
        }
      }
      os.flush();
      process.waitFor();
      process.destroy();
      if (process.exitValue() != 0) {
        throw new RuntimeException("Exception while executing process.");
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
