package com.example.starter;

import java.util.List;

public interface ProcessExecutorAsRoot {
  void execute(ProcessBuilder processBuilder);
  void execute(ProcessBuilder processBuilder, List<String> inputStrings);
}
