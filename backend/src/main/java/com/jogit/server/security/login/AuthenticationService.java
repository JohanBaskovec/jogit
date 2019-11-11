package com.jogit.server.security.login;

import com.jogit.server.security.hashing.PgHashStrategy;
import com.jogit.server.security.user.UserRepository;
import com.jogit.server.gprc.LoginRequest;
import com.jogit.server.gprc.User;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;

public interface AuthenticationService {
  /**
   * Set the hash strategy to use. Use this if you want override the default hash strategy
   * @param strategy  the strategy
   * @return a reference to this for fluency
   */
  AuthenticationService setHashStrategy(PgHashStrategy strategy);

  /**
   * Compute the hashed password given the unhashed password and the salt without nonce
   *
   * The implementation relays to the JDBCHashStrategy provided.
   *
   * @param password  the unhashed password
   * @param salt  the salt
   * @return  the hashed password
   */
  default String computeHash(String password, String salt) {
    return computeHash(password, salt, -1);
  }

  /**
   * Compute the hashed password given the unhashed password and the salt
   *
   * The implementation relays to the JDBCHashStrategy provided.
   *
   * @param password  the unhashed password
   * @param salt  the salt
   * @param version the nonce version to use
   * @return  the hashed password
   */
  String computeHash(String password, String salt, int version);

  /**
   * Compute a salt string.
   *
   * The implementation relays to the JDBCHashStrategy provided.
   *
   * @return a non null salt value
   */
  String generateSalt();

  /**
   * Provide a application configuration level on hash nonce's as a ordered list of
   * nonces where each position corresponds to a version.
   *
   * The nonces are supposed not to be stored in the underlying jdbc storage but to
   * be provided as a application configuration. The idea is to add one extra variable
   * to the hash function in order to make breaking the passwords using rainbow tables
   * or precomputed hashes harder. Leaving the attacker only with the brute force
   * approach.
   *
   * The implementation relays to the JDBCHashStrategy provided.
   *
   * @param nonces a List of non null Strings.
   * @return a reference to this for fluency
   */
  AuthenticationService setNonces(JsonArray nonces);

  void authenticate(
    UserRepository userRepository,
    LoginRequest loginRequest,
    Handler<AsyncResult<User>> resultHandler
  );
}
