package com.example.starter;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemServiceImpl implements FileSystemService {
  private final Path appFileSystemRoot = Paths.get("/jogit/git/");
  private final ProcessExecutorAsRoot processExecutorAsRoot;

  public FileSystemServiceImpl(
    ProcessExecutorAsRoot processExecutorAsRoot
  ) {
    this.processExecutorAsRoot = processExecutorAsRoot;
  }

  @SuppressWarnings("UnstableApiUsage")
  public String readResourceToString(String resourcePath) throws IOException {
    return Resources.toString(
      Resources.getResource(resourcePath),
      Charsets.UTF_8
    );
  }

  /*
   * Return true if folder is child of another one.
   */
  public boolean isChildOfFileSystemRoot(Path child) {
    return child.toAbsolutePath().startsWith(appFileSystemRoot.toAbsolutePath());
  }

  public void createDirectoryAsRoot(File directory) {
    if (directory == null) {
      throw new NullPointerException("directory must not be null");
    }
    if (!isChildOfFileSystemRoot(directory.toPath())) {
      throw new RuntimeException("Can't create directory outside of repositories root directory!");
    }

    processExecutorAsRoot.execute(new ProcessBuilder()
      .command("mkdir", directory.getAbsolutePath()));
  }

  public void changeOwnerAndGroup(String userName, String group, File directory) {
    if (userName == null) {
      throw new NullPointerException("userName must not be null");
    }
    if (group == null) {
      throw new NullPointerException("group must not be null");
    }
    if (directory == null) {
      throw new NullPointerException("directory must not be null");
    }
    if (!isChildOfFileSystemRoot(directory.toPath())) {
      throw new RuntimeException("Can't change owner outside of repositories root directory!");
    }
    processExecutorAsRoot.execute(
      new ProcessBuilder()
        .command("chown", "-R", userName + ":" + group, directory.getAbsolutePath())
    );
  }

  public Path getAppFileSystemRoot() {
    return appFileSystemRoot;
  }
}
