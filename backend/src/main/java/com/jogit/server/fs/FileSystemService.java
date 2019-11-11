package com.jogit.server.fs;

import java.io.File;
import java.nio.file.Path;

public interface FileSystemService {
  boolean isChildOfFileSystemRoot(Path child);
  Path getAppFileSystemRoot();
  void createDirectoryAsRoot(File directory);
  void changeOwnerAndGroup(String userName, String group, File directory);
}
