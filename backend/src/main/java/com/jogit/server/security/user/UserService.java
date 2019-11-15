package com.jogit.server.security.user;

import com.jogit.server.grpc.User;
import io.vertx.sqlclient.Row;

public class UserService {
  public User fromRow(Row row) {
    User.Builder userBuilder = User.newBuilder()
      .setUsername(row.getString("user_username"));
    String salt = row.getString("user_password_salt");
    String passwordHash = row.getString("user_password");
    if (salt != null) {
      userBuilder.setPasswordSalt(salt);
    }
    if (passwordHash != null) {
      userBuilder.setPassword(passwordHash);
    }
    return userBuilder.build();
  }
}
