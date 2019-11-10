package com.example.starter;

import com.example.starter.gprc.FileMetadata;
import com.example.starter.gprc.GitRepository;
import io.vertx.sqlclient.Row;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
    if (!directory.exists()) {
      fileSystemService.createDirectoryAsRoot(directory);
      fileSystemService.changeOwnerAndGroup(userName, userName, directory);
    }
  }

  private File getUserRootDirectory(String userName) {
    Path userRepositoryRootPath = repositoryRoot.resolve(userName);
    File userRepositoryRootDirectory = userRepositoryRootPath.toFile();
    return userRepositoryRootDirectory;
  }

  private File getRepositoryDirectory(GitRepository gitRepository, String directoryPath) {
    Path userRepositoryRootPath = repositoryRoot
      .resolve(gitRepository.getUserUserName())
      .resolve(gitRepository.getName())
      .resolve(directoryPath);
    return userRepositoryRootPath.toFile();
  }

  private File getOrCreateUserDirectory(String userName) {
    File userRepositoryRootDirectory = getUserRootDirectory(userName);
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
        .command("git", "init", "--bare")
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

  public GitRepository gitRepositoryFromRow(Row row) {
    return GitRepository.newBuilder()
      .setName(row.getString("git_repository_name"))
      .setUserUserName(row.getString("git_repository_user_username"))
      .build();
  }

  public List<FileMetadata> getDirectoryContent(GitRepository repository, String directoryPath) {
    if (directoryPath.startsWith("/")) {
      throw new RuntimeException("directory path must not start with '/'");
    }
    //String directoryPathEscaped = "\"" + directoryPath.replace("\"", "\\\"") + "\"";
    File directory = getRepositoryDirectory(repository, directoryPath);
    if (!directory.exists()) {
      throw new RuntimeException("Can't get directory content: directory " + directory + " does not exist.");
    }
    String lsOutput = processExecutorAsRoot.execute(
      new ProcessBuilder()
        .directory(directory)
        .command("git", "ls-tree", "--full-tree", "-l", "HEAD")
    );

    String[] lines = lsOutput.split("\n");
    List<FileMetadata> files = new ArrayList<>();

    for (String line : lines) {
      String[] lineParts = line.split("\\s+");
      boolean partsStarted = false;
      int dataIndex = 0;
      FileMetadata.Builder fileMetadata = FileMetadata.newBuilder();
      for (String linePart: lineParts) {
        if (linePart.length() != 0) {
          switch (dataIndex) {
            case 0:
              // permissions; ignore
              break;
            case 1:
              fileMetadata.setType(linePart);
              break;
            case 2:
              fileMetadata.setSha1(linePart);
              break;
            case 3:
              int sizeInt = 0;
              if (!linePart.equals("-")) {
                sizeInt = Integer.parseInt(linePart);
              }
              fileMetadata.setSize(sizeInt);
              break;
            case 4:
              fileMetadata.setName(linePart);
              break;
          }
          dataIndex++;
        }
      }
      files.add(fileMetadata.build());
    }

    return files;
  }
}
