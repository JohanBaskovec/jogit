package com.example.starter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GitRepositoryService {
  public static final Path repositoryRoot = Paths.get("/srv/jogit/git");
  String password;

  GitRepositoryService(String rootPassword) {
    this.password = rootPassword + "\n";
  }

  /**
   * Execute a process as root. processBuilder.command() must already be
   * have been called and and start with "sudo" "-S".
   *
   * @param processBuilder
   */
  private void executeProcessAsRootBlocking(ProcessBuilder processBuilder) {
    try {
      processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT);
      Process process = processBuilder.start();
      OutputStream os = process.getOutputStream();
      os.write(password.getBytes());
      os.flush();
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void createDirectoryAsRoot(File directory) {
    executeProcessAsRootBlocking(new ProcessBuilder()
      .directory(repositoryRoot.toFile())
      .command("sudo", "-S", "mkdir", directory.getAbsolutePath()));
  }

  private void changeOwnerAndGroupBlocking(String userName, String group, File directory) {
    executeProcessAsRootBlocking(
      new ProcessBuilder().directory(repositoryRoot.toFile())
        .command("sudo", "-S", "chown", "-R", userName + ":" + group, directory.getAbsolutePath())
    );
  }

  private void createUserOwnedDirectoryIfItDoesntExist(File directory, String userName) {
    createDirectoryAsRoot(directory);
    changeOwnerAndGroupBlocking(userName, userName, directory);
  }

  private File getOrCreateUserDirectory(String userName) {
    Path userRepositoryRootPath = repositoryRoot.resolve(userName);
    File userRepositoryRootDirectory = userRepositoryRootPath.toFile();
    createUserOwnedDirectoryIfItDoesntExist(userRepositoryRootDirectory, userName);
    return userRepositoryRootDirectory;
  }

  private File createRepositoryDirectory(
    File userDirectory,
    String userName,
    String repositoryName
  ) {
    Path repositoryPath = userDirectory.toPath().resolve(repositoryName);
    File repositoryDirectory = repositoryPath.toFile();
    createUserOwnedDirectoryIfItDoesntExist(repositoryDirectory, userName);
    return repositoryDirectory;
  }

  private void initializeGitBareRepository(File repositoryDirectory, String userName) {
    System.out.println(repositoryDirectory);
    executeProcessAsRootBlocking(
      new ProcessBuilder()
        .directory(repositoryDirectory)
        .command("sudo", "-S", "git", "init", "--bare")
    );
    changeOwnerAndGroupBlocking(userName, userName, repositoryDirectory);
  }

  void createRepository(String userName, String repositoryName) {
    File userDirectory = getOrCreateUserDirectory(userName);
    File repositoryDirectory = createRepositoryDirectory(userDirectory, userName, repositoryName);
    initializeGitBareRepository(repositoryDirectory, userName);
  }
}
