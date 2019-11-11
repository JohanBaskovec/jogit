package com.jogit.server.security.session;

import com.jogit.server.security.user.UserService;
import com.jogit.server.gprc.Session;
import io.vertx.ext.auth.PRNG;
import io.vertx.sqlclient.Row;

public class SessionService {
  private final UserService userService;
  private final PRNG rng;
  private static final char[] HEX = "0123456789abcdef".toCharArray();

  public SessionService(UserService userService, PRNG rng) {
    this.userService = userService;
    this.rng = rng;
  }

  Session fromRow(Row row) {
    return Session.newBuilder()
      .setId(row.getString("session_id"))
      .setUser(this.userService.fromRow(row))
      .setUserUsername(row.getString("session_user_username"))
      .build()
      ;
  }

  public String generateSessionId() {
    int length = 64;
    final byte[] bytes = new byte[length];
    rng.nextBytes(bytes);

    final char[] hex = new char[length * 2];
    for (int j = 0; j < length; j++) {
      int v = bytes[j] & 0xFF;
      hex[j * 2] = HEX[v >>> 4];
      hex[j * 2 + 1] = HEX[v & 0x0F];
    }

    return new String(hex);
  }
}
