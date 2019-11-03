package com.example.starter;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
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
      String confFilePath = System.getenv("JOGIT_CONF");
      JsonObject config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
      DatabaseService databaseService = new DatabaseService();
      pgClient = databaseService.newPgPool(vertx, config.getJsonObject("database"));
      VertxTestContext finalTestContext = testContext;
      pgClient.begin(testContext.succeeding((Transaction transaction) -> {
        System.out.println("initTestContext: begin transaction");
        transaction.query("delete from session; delete from appuser;", finalTestContext.succeeding(event -> {
          System.out.println("deleted everything.");
          transaction.commit(finalTestContext.succeeding(transactionCommit -> {
            System.out.println("transaction commited.");
            vertx.deployVerticle(new MainVerticle(), finalTestContext.succeeding(id -> {
              System.out.println("deployed vertice");
              serverStarted = true;
              channel = VertxChannelBuilder
                .forAddress(vertx, "localhost", 8888)
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
