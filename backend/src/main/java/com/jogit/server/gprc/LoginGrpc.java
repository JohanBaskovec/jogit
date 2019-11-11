package com.jogit.server.gprc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: login.proto")
public final class LoginGrpc {

  private LoginGrpc() {}

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

  public static final String SERVICE_NAME = "helloworld.Login";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.jogit.server.gprc.LoginRequest,
      com.jogit.server.gprc.LoginReply> getLoginMethod;

  public static io.grpc.MethodDescriptor<com.jogit.server.gprc.LoginRequest,
      com.jogit.server.gprc.LoginReply> getLoginMethod() {
    io.grpc.MethodDescriptor<com.jogit.server.gprc.LoginRequest, com.jogit.server.gprc.LoginReply> getLoginMethod;
    if ((getLoginMethod = LoginGrpc.getLoginMethod) == null) {
      synchronized (LoginGrpc.class) {
        if ((getLoginMethod = LoginGrpc.getLoginMethod) == null) {
          LoginGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.jogit.server.gprc.LoginRequest, com.jogit.server.gprc.LoginReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Login", "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.gprc.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.gprc.LoginReply.getDefaultInstance()))
                  .setSchemaDescriptor(new LoginMethodDescriptorSupplier("Login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LoginStub newStub(io.grpc.Channel channel) {
    return new LoginStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LoginBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LoginBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LoginFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LoginFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static LoginVertxStub newVertxStub(io.grpc.Channel channel) {
    return new LoginVertxStub(channel);
  }

  /**
   */
  public static abstract class LoginImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(com.jogit.server.gprc.LoginRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.gprc.LoginReply> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.jogit.server.gprc.LoginRequest,
                com.jogit.server.gprc.LoginReply>(
                  this, METHODID_LOGIN)))
          .build();
    }
  }

  /**
   */
  public static final class LoginStub extends io.grpc.stub.AbstractStub<LoginStub> {
    public LoginStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LoginStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoginStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LoginStub(channel, callOptions);
    }

    /**
     */
    public void login(com.jogit.server.gprc.LoginRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.gprc.LoginReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LoginBlockingStub extends io.grpc.stub.AbstractStub<LoginBlockingStub> {
    public LoginBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LoginBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoginBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LoginBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.jogit.server.gprc.LoginReply login(com.jogit.server.gprc.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LoginFutureStub extends io.grpc.stub.AbstractStub<LoginFutureStub> {
    public LoginFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LoginFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoginFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LoginFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jogit.server.gprc.LoginReply> login(
        com.jogit.server.gprc.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class LoginVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(com.jogit.server.gprc.LoginRequest request,
        io.vertx.core.Future<com.jogit.server.gprc.LoginReply> response) {
      asyncUnimplementedUnaryCall(getLoginMethod(), LoginGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.jogit.server.gprc.LoginRequest,
                com.jogit.server.gprc.LoginReply>(
                  this, METHODID_LOGIN)))
          .build();
    }
  }

  /**
   */
  public static final class LoginVertxStub extends io.grpc.stub.AbstractStub<LoginVertxStub> {
    public LoginVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public LoginVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoginVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LoginVertxStub(channel, callOptions);
    }

    /**
     */
    public void login(com.jogit.server.gprc.LoginRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.jogit.server.gprc.LoginReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, LoginGrpc.toObserver(response));
    }
  }

  private static final int METHODID_LOGIN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LoginImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LoginImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.jogit.server.gprc.LoginRequest) request,
              (io.grpc.stub.StreamObserver<com.jogit.server.gprc.LoginReply>) responseObserver);
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
    private final LoginVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(LoginVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.jogit.server.gprc.LoginRequest) request,
              (io.vertx.core.Future<com.jogit.server.gprc.LoginReply>) io.vertx.core.Future.<com.jogit.server.gprc.LoginReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.jogit.server.gprc.LoginReply>) responseObserver).onNext(ar.result());
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

  private static abstract class LoginBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LoginBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.jogit.server.gprc.LoginProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Login");
    }
  }

  private static final class LoginFileDescriptorSupplier
      extends LoginBaseDescriptorSupplier {
    LoginFileDescriptorSupplier() {}
  }

  private static final class LoginMethodDescriptorSupplier
      extends LoginBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LoginMethodDescriptorSupplier(String methodName) {
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
      synchronized (LoginGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LoginFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .build();
        }
      }
    }
    return result;
  }
}
