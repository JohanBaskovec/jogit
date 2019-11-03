package com.example.starter;

import com.example.starter.gprc.GreeterGrpc;
import com.example.starter.gprc.HelloRequest;
import io.grpc.Channel;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class GreeterTest extends IntegrationTest {
  @Test
  void test(VertxTestContext testContext) throws Throwable {
    initTestContext(testContext, (Channel channel) -> {
      GreeterGrpc.GreeterVertxStub stub = GreeterGrpc.newVertxStub(channel);
      HelloRequest request = HelloRequest.newBuilder()
        .setName("Julien")
        .setSessionToken("16ee426f-b19f-43de-a5d2-db660f846614")
        .build();

      // Call the remote service
      stub.sayHello(request, ar -> {
        if (ar.succeeded()) {
          System.out.println("Got the server response: " + ar.result().getMessage());
          testContext.completeNow();
        } else {
          System.out.println("Coult not reach server " + ar.cause().getMessage());
        }
      });
    });
    assertThat(testContext.awaitCompletion(5, TimeUnit.SECONDS)).isTrue();
    if (testContext.failed()) {
      throw testContext.causeOfFailure();
    }
  }
}
