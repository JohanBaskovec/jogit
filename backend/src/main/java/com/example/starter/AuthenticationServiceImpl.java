package com.example.starter;

import com.example.starter.gprc.LoginRequest;
import com.example.starter.gprc.User;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;

public class AuthenticationServiceImpl implements AuthenticationService {
  private PgHashStrategy strategy;

  public AuthenticationServiceImpl(Vertx vertx) {
    strategy = PgHashStrategy.createPBKDF2(vertx);
  }

  public void authenticate(
    UserRepository userRepository,
    LoginRequest loginRequest,
    Handler<AsyncResult<User>> resultHandler
  ) {
    userRepository.getByUsername(
      loginRequest.getUsername(),
      (AsyncResult<User> userResult) -> {
        if (userResult.failed()) {
          System.out.println("Could not get user.");
          userResult.cause().printStackTrace();
          resultHandler.handle(userResult);
          return;
        }
        User user = userResult.result();
        if (user != null) {
          // extract the version (-1 means no version)
          int version = -1;
          int sep = user.getPassword().lastIndexOf('$');
          if (sep != -1) {
            try {
              version = Integer.parseInt(user.getPassword().substring(sep + 1));
            } catch (NumberFormatException e) {
              // the nonce version is not a number
              resultHandler.handle(Future.failedFuture("Invalid nonce version: " + version));
              return;
            }
          }
          String hashedPassword = computeHash(loginRequest.getPassword(), user.getPasswordSalt(), version);
          if (PgHashStrategy.isEqual(user.getPassword(), hashedPassword)) {
            resultHandler.handle(Future.succeededFuture(user));
          } else {
            resultHandler.handle(Future.failedFuture("Invalid username/password"));
          }
        } else {
          resultHandler.handle(Future.failedFuture("Invalid username/password"));
        }
      });
  }

  @Override
  public AuthenticationService setHashStrategy(PgHashStrategy strategy) {
    this.strategy = strategy;
    return this;
  }

  @Override
  public String computeHash(String password, String salt, int version) {
    return strategy.computeHash(password, salt, version);
  }

  @Override
  public String generateSalt() {
    return strategy.generateSalt();
  }

  @Override
  public AuthenticationService setNonces(JsonArray nonces) {
    strategy.setNonces(nonces);
    return this;
  }
}
