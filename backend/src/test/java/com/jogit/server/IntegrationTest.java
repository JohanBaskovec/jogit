package com.jogit.server;

import com.jogit.server.database.DatabaseService;
import com.jogit.server.linux.LinuxService;
import com.jogit.server.linux.ProcessExecutorAsRoot;
import com.jogit.server.linux.ProcessExecutorAsRootImpl;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.grpc.VertxChannelBuilder;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class IntegrationTest {
  private static boolean serverStarted = false;
  private static Vertx vertx;
  private static ManagedChannel channel;
  private static PgPool pgClient;

  @BeforeEach()
  void beforeEach() {
    System.out.println("before each");
  }

  void initTestContext(VertxTestContext testContext, Handler<Channel> handler) {
    if (testContext == null) {
      testContext = new VertxTestContext();
    }
    if (!serverStarted) {
      vertx = Vertx.vertx();
      String confFilePath = System.getenv("JOGIT_TEST_CONF");
      JsonObject config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
      Integer port = config.getJsonObject("server").getInteger("port");
      if (port == null) {
        testContext.failNow(new RuntimeException("port must not be null."));
        return;
      }
      ProcessExecutorAsRoot processExecutorAsRoot = new ProcessExecutorAsRootImpl(config.getString("rootPassword"));
      DatabaseService databaseService = new DatabaseService();
      pgClient = databaseService.newPgPool(vertx, config.getJsonObject("database"));
      VertxTestContext finalTestContext = testContext;
      pgClient.begin(testContext.succeeding((Transaction transaction) -> {
        System.out.println("initTestContext: begin transaction");
        transaction.query("delete from session; delete from appuser;", finalTestContext.succeeding(event -> {
          System.out.println("cleared database.");
          LinuxService linuxService = new LinuxService(processExecutorAsRoot);
          linuxService.deleteUserAccount("username");
          transaction.commit(finalTestContext.succeeding(transactionCommit -> {
            System.out.println("transaction commited.");
            DeploymentOptions deploymentOptions = new DeploymentOptions();
            deploymentOptions.setConfig(config);
            vertx.deployVerticle(new MainVerticle(), deploymentOptions, finalTestContext.succeeding(id -> {
              System.out.println("deployed vertice");
              serverStarted = true;
              channel = VertxChannelBuilder
                .forAddress(vertx, "localhost", port)
                .usePlaintext(true)
                .build();
              handler.handle(channel);
            }));
          }));
        }));
      }));
    } else {
      handler.handle(channel);
    }
  }
}
