package com.example.starter;

import com.example.starter.gprc.GreeterGrpc;
import com.example.starter.gprc.HelloReply;
import com.example.starter.gprc.HelloRequest;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) {
    GreeterGrpc.GreeterVertxImplBase service = new GreeterGrpc.GreeterVertxImplBase() {
      @Override
      public void sayHello(HelloRequest request, Future<HelloReply> future) {
        future.complete(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
      }
    };
    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx, "localhost", 8888)
      .addService(service)
      .build();

    rpcServer.start(
      (AsyncResult<Void> rpcServerStart) -> {
        if (rpcServerStart.succeeded()) {
          startPromise.complete();
          System.out.println("Server started on port 8888");
        } else {
          startPromise.fail(rpcServerStart.cause());
        }
      });
  }
}
