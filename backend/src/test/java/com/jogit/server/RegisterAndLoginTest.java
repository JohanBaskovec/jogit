package com.jogit.server;

import com.jogit.server.grpc.GetCurrentSessionReply;
import com.jogit.server.grpc.GetCurrentSessionRequest;
import com.jogit.server.grpc.LoginGrpc;
import com.jogit.server.grpc.LoginReply;
import com.jogit.server.grpc.LoginRequest;
import com.jogit.server.grpc.RegisterGrpc;
import com.jogit.server.grpc.RegisterReply;
import com.jogit.server.grpc.RegisterRequest;
import com.jogit.server.grpc.SessionServiceGrpc;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    assertThat(testContext.awaitCompletion(5, TimeUnit.SECONDS)).isTrue();
    if (testContext.failed()) {
      throw testContext.causeOfFailure();
    }
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
      public void onNext(LoginReply loginReply) {
        getCurrentSession(testContext, channel, loginReply.getSession().getId());
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

  private void getCurrentSession(
    VertxTestContext testContext,
    Channel channel,
    String sessionToken
  ) {
    GetCurrentSessionRequest getCurrentSessionRequest = GetCurrentSessionRequest
      .newBuilder()
      .setSessionToken(sessionToken)
      .build();
    SessionServiceGrpc.SessionServiceStub sessionServiceStub = SessionServiceGrpc.newStub(channel);
    sessionServiceStub.getCurrentSession(getCurrentSessionRequest, new StreamObserver<GetCurrentSessionReply>() {
      @Override
      public void onNext(GetCurrentSessionReply value) {
        testContext.verify(() -> {
          assertTrue(value.hasSession());
          testContext.completeNow();
        });
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
