package com.example.starter;

import com.example.starter.gprc.GreeterGrpc;
import com.example.starter.gprc.HelloRequest;
import io.grpc.ManagedChannel;
import io.vertx.core.Vertx;
import io.vertx.grpc.VertxChannelBuilder;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(VertxExtension.class)
class GreeterTest {
  @Test
  void test(Vertx vertx, VertxTestContext testContext) throws Throwable {
    ManagedChannel channel = VertxChannelBuilder
      .forAddress(vertx, "localhost", 8888)
      .usePlaintext(true)
      .build();

    // Get a stub to use for interacting with the remote service
    GreeterGrpc.GreeterVertxStub stub = GreeterGrpc.newVertxStub(channel);
    HelloRequest request = HelloRequest.newBuilder().setName("Julien").build();

    // Call the remote service
    stub.sayHello(request, ar -> {
      if (ar.succeeded()) {
        System.out.println("Got the server response: " + ar.result().getMessage());
        testContext.completeNow();
      } else {
        System.out.println("Coult not reach server " + ar.cause().getMessage());
      }
    });
    assertThat(testContext.awaitCompletion(5, TimeUnit.SECONDS)).isTrue();
    if (testContext.failed()) {
      throw testContext.causeOfFailure();
    }
  }
}
