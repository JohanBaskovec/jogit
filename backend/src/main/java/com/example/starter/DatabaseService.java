package com.example.starter;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class DatabaseService {
  PgPool newPgPool(Vertx vertx, JsonObject config) {
    PgConnectOptions pgConnectOptions = new PgConnectOptions(config);
    PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
    PgPool pool = PgPool.pool(vertx, pgConnectOptions, poolOptions);
    return pool;
  }
}
