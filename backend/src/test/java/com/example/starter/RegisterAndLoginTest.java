package com.example.starter;

import com.example.starter.gprc.LoginGrpc;
import com.example.starter.gprc.LoginReply;
import com.example.starter.gprc.LoginRequest;
import com.example.starter.gprc.RegisterGrpc;
import com.example.starter.gprc.RegisterReply;
import com.example.starter.gprc.RegisterRequest;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterAndLoginTest extends IntegrationTest {
  @Test
  void test(VertxTestContext testContext) throws Throwable {
    // Get a stub to use for interacting with the remote service
    initTestContext(testContext, (Channel channel) -> {
      RegisterGrpc.RegisterStub stub = RegisterGrpc.newStub(channel);
      String username = "username";
      String password = "password";
      RegisterRequest registerRequest = RegisterRequest.newBuilder()
        .setUsername(username)
        .setPassword(password)
        .build();

      // Call the remote service
      stub.register(registerRequest, new StreamObserver<RegisterReply>() {
        @Override
        public void onNext(RegisterReply value) {
          tryToLogin(testContext, channel, username, password);
        }

        @Override
        public void onError(Throwable t) {
          testContext.failNow(t);
        }

        @Override
        public void onCompleted() {
          System.out.println("stub.register complete");
        }
      });
    });
    /*
    assertThat(testContext.awaitCompletion(5, TimeUnit.SECONDS)).isTrue();
    if (testContext.failed()) {
      throw testContext.causeOfFailure();
    }

     */
  }

  private void tryToLogin(
    VertxTestContext testContext,
    Channel channel,
    String username,
    String password
  ) {
    LoginRequest loginRequest = LoginRequest.newBuilder()
      .setUsername(username)
      .setPassword(password)
      .build();
    LoginGrpc.LoginStub stub = LoginGrpc.newStub(channel);
    stub.login(loginRequest, new StreamObserver<LoginReply>() {
      @Override
      public void onNext(LoginReply value) {
        testContext.completeNow();
      }

      @Override
      public void onError(Throwable t) {
        testContext.failNow(t);
      }

      @Override
      public void onCompleted() {

      }
    });
  }
}
