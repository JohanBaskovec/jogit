package com.example.starter;

import com.example.starter.gprc.User;
import io.vertx.sqlclient.Row;

public class UserService {
  User fromRow(Row row) {
    return User.newBuilder()
      .setUsername(row.getString("user_username"))
      .setPasswordSalt(row.getString("user_password_salt"))
      .setPassword(row.getString("user_password"))
      .build();
  }
}
