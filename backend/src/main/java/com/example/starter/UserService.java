package com.example.starter;

import com.example.starter.gprc.User;
import io.vertx.sqlclient.Row;

public class UserService {
  User fromRow(Row row) {
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
