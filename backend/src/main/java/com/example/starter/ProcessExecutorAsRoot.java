package com.example.starter;

import java.util.List;

public interface ProcessExecutorAsRoot {
  String execute(ProcessBuilder processBuilder);
  String execute(ProcessBuilder processBuilder, List<String> inputStrings);
}
