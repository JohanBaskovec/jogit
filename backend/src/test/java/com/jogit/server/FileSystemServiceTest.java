package com.jogit.server;

import com.jogit.server.fs.FileSystemService;
import com.jogit.server.fs.FileSystemServiceImpl;
import com.jogit.server.linux.ProcessExecutorAsRoot;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class FileSystemServiceTest {
  @Test
  void isChildOfRepositoryRoot() {
    FileSystemService fileSystemService = new FileSystemServiceImpl(null);
    File file = new File("/srv/jogit/git/test");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/srv/jogit/git/test2");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/srv/jogit/git/test2/test3");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/srv/jogit/git/");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/srv/jogit/gi/test3");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/srv/jogit/gi/test3/test");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/srv/jogit/test");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/srv/jogit");
    assertTrue(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/usr/app");
    assertFalse(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/");
    assertFalse(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("");
    assertFalse(fileSystemService.isChildOfFileSystemRoot(file.toPath()));

    file = new File("/home");
    assertFalse(fileSystemService.isChildOfFileSystemRoot(file.toPath()));
  }

  @Test
  void testCreateDirectoryAsRoot() {
    FileSystemService fileSystemService = new FileSystemServiceImpl(mock(ProcessExecutorAsRoot.class));
    assertThrows(
      RuntimeException.class,
      () -> fileSystemService.createDirectoryAsRoot(new File("/jogittest")),
      "Trying to create a directory as root outside of the app's file system should throw.");

    assertDoesNotThrow(
      () -> fileSystemService.createDirectoryAsRoot(new File("/srv/jogit/git/test")),
      "Trying to create a directory as root in the app's file system should not throw.");
  }

  @Test
  void changeOwnerAndGroup() {
    FileSystemService fileSystemService = new FileSystemServiceImpl(mock(ProcessExecutorAsRoot.class));
    assertThrows(
      RuntimeException.class,
      () -> fileSystemService.changeOwnerAndGroup("test", "test", new File("/jogittest")),
      "Trying to create a directory as root outside of the app's file system should throw.");

    assertDoesNotThrow(
      () -> fileSystemService.changeOwnerAndGroup("test", "test", new File("/srv/jogit/git/test")),
      "Trying to create a directory as root in the app's file system should not throw.");
  }
}
