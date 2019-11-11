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
  public String execute(ProcessBuilder processBuilder) {
    return execute(processBuilder, null);
  }

  public String execute(ProcessBuilder processBuilder, List<String> inputStrings) {
    String output = "";
    try {
      ArrayList<String> commandWords = new ArrayList<>();
      // -k forces the user to type their password even if they did it recently
      commandWords.add("sudo");
      commandWords.add("-kS");
      commandWords.addAll(processBuilder.command());
      processBuilder.command(commandWords);

      Process process = processBuilder.redirectErrorStream(true).start();

      BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      StringBuilder sb = new StringBuilder();

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

      boolean firstLine = true;
      while ((line = br.readLine()) != null) {
        if (firstLine) {
          // the first colon is the one at the end of "[sudo] password for <username>:"
          // we ignore it
          int indexOfColon = line.indexOf(':');
          sb.append(line.substring(indexOfColon + 1));
          sb.append("\n");
          firstLine = false;
          continue;
        }
        sb.append(line);
        sb.append("\n");
      }
      output = sb.toString();
      process.destroy();
      if (process.exitValue() != 0) {
        throw new RuntimeException(output);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return output;
  }
}
