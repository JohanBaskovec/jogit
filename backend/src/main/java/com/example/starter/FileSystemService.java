package com.example.starter;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public class FileSystemService {
  @SuppressWarnings("UnstableApiUsage")
  String readResourceToString(String resourcePath) throws IOException {
    return Resources.toString(
      Resources.getResource(resourcePath),
      Charsets.UTF_8
    );
  }
}
