package com.example.starter;

import java.io.File;
import java.nio.file.Path;

public class GitRepositoryService {
  private final FileSystemService fileSystemService;
  private final Path repositoryRoot;
  private final ProcessExecutorAsRoot processExecutorAsRoot;

  GitRepositoryService(
    FileSystemService fileSystemService,
    ProcessExecutorAsRoot processExecutorAsRoot
  ) {
    this.fileSystemService = fileSystemService;
    this.repositoryRoot = fileSystemService.getAppFileSystemRoot().resolve("git");
    this.processExecutorAsRoot = processExecutorAsRoot;
  }

  private void createUserOwnedDirectoryIfItDoesntExist(File directory, String userName) {
    fileSystemService.createDirectoryAsRoot(directory);
    fileSystemService.changeOwnerAndGroup(userName, userName, directory);
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
    processExecutorAsRoot.execute(
      new ProcessBuilder()
        .directory(repositoryDirectory)
        .command("git init --bare")
    );
    fileSystemService.changeOwnerAndGroup(userName, userName, repositoryDirectory);
  }

  void createRepository(String userName, String repositoryName) {
    if (userName == null) {
      throw new NullPointerException("userName must not be null");
    }
    if (repositoryName == null) {
      throw new NullPointerException("repositoryName must not be null");
    }
    File userDirectory = getOrCreateUserDirectory(userName);
    File repositoryDirectory = createRepositoryDirectory(userDirectory, userName, repositoryName);
    initializeGitBareRepository(repositoryDirectory, userName);
  }
}
