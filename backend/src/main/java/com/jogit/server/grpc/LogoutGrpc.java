package com.jogit.server.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: logout.proto")
public final class LogoutGrpc {

  private LogoutGrpc() {}

  private static <T> io.grpc.stub.StreamObserver<T> toObserver(final io.vertx.core.Handler<io.vertx.core.AsyncResult<T>> handler) {
    return new io.grpc.stub.StreamObserver<T>() {
      private volatile boolean resolved = false;
      @Override
      public void onNext(T value) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture(value));
        }
      }

      @Override
      public void onError(Throwable t) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.failedFuture(t));
        }
      }

      @Override
      public void onCompleted() {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture());
        }
      }
    };
  }

  public static final String SERVICE_NAME = "helloworld.Logout";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.jogit.server.grpc.LogoutRequest,
      com.jogit.server.grpc.LogoutReply> getLogoutMethod;

  public static io.grpc.MethodDescriptor<com.jogit.server.grpc.LogoutRequest,
      com.jogit.server.grpc.LogoutReply> getLogoutMethod() {
    io.grpc.MethodDescriptor<com.jogit.server.grpc.LogoutRequest, com.jogit.server.grpc.LogoutReply> getLogoutMethod;
    if ((getLogoutMethod = LogoutGrpc.getLogoutMethod) == null) {
      synchronized (LogoutGrpc.class) {
        if ((getLogoutMethod = LogoutGrpc.getLogoutMethod) == null) {
          LogoutGrpc.getLogoutMethod = getLogoutMethod = 
              io.grpc.MethodDescriptor.<com.jogit.server.grpc.LogoutRequest, com.jogit.server.grpc.LogoutReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Logout", "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.LogoutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.LogoutReply.getDefaultInstance()))
                  .setSchemaDescriptor(new LogoutMethodDescriptorSupplier("Logout"))
                  .build();
          }
        }
     }
     return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LogoutStub newStub(io.grpc.Channel channel) {
    return new LogoutStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LogoutBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LogoutBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LogoutFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LogoutFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static LogoutVertxStub newVertxStub(io.grpc.Channel channel) {
    return new LogoutVertxStub(channel);
  }

  /**
   */
  public static abstract class LogoutImplBase implements io.grpc.BindableService {

    /**
     */
    public void logout(com.jogit.server.grpc.LogoutRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.LogoutReply> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.jogit.server.grpc.LogoutRequest,
                com.jogit.server.grpc.LogoutReply>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   */
  public static final class LogoutStub extends io.grpc.stub.AbstractStub<LogoutStub> {
    public LogoutStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LogoutStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogoutStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogoutStub(channel, callOptions);
    }

    /**
     */
    public void logout(com.jogit.server.grpc.LogoutRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.LogoutReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LogoutBlockingStub extends io.grpc.stub.AbstractStub<LogoutBlockingStub> {
    public LogoutBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LogoutBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogoutBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogoutBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.jogit.server.grpc.LogoutReply logout(com.jogit.server.grpc.LogoutRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LogoutFutureStub extends io.grpc.stub.AbstractStub<LogoutFutureStub> {
    public LogoutFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LogoutFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogoutFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogoutFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jogit.server.grpc.LogoutReply> logout(
        com.jogit.server.grpc.LogoutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class LogoutVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void logout(com.jogit.server.grpc.LogoutRequest request,
        io.vertx.core.Future<com.jogit.server.grpc.LogoutReply> response) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), LogoutGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.jogit.server.grpc.LogoutRequest,
                com.jogit.server.grpc.LogoutReply>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   */
  public static final class LogoutVertxStub extends io.grpc.stub.AbstractStub<LogoutVertxStub> {
    public LogoutVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LogoutVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogoutVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogoutVertxStub(channel, callOptions);
    }

    /**
     */
    public void logout(com.jogit.server.grpc.LogoutRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.jogit.server.grpc.LogoutReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, LogoutGrpc.toObserver(response));
    }
  }

  private static final int METHODID_LOGOUT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LogoutImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LogoutImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGOUT:
          serviceImpl.logout((com.jogit.server.grpc.LogoutRequest) request,
              (io.grpc.stub.StreamObserver<com.jogit.server.grpc.LogoutReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class VertxMethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LogoutVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(LogoutVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGOUT:
          serviceImpl.logout((com.jogit.server.grpc.LogoutRequest) request,
              (io.vertx.core.Future<com.jogit.server.grpc.LogoutReply>) io.vertx.core.Future.<com.jogit.server.grpc.LogoutReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.jogit.server.grpc.LogoutReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LogoutBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LogoutBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.jogit.server.grpc.LogoutProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Logout");
    }
  }

  private static final class LogoutFileDescriptorSupplier
      extends LogoutBaseDescriptorSupplier {
    LogoutFileDescriptorSupplier() {}
  }

  private static final class LogoutMethodDescriptorSupplier
      extends LogoutBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LogoutMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LogoutGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LogoutFileDescriptorSupplier())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
