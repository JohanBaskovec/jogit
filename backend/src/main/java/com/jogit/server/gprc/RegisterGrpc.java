package com.jogit.server.gprc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * WRTF
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: register.proto")
public final class RegisterGrpc {

  private RegisterGrpc() {}

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

  public static final String SERVICE_NAME = "helloworld.Register";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.jogit.server.gprc.RegisterRequest,
      com.jogit.server.gprc.RegisterReply> getRegisterMethod;

  public static io.grpc.MethodDescriptor<com.jogit.server.gprc.RegisterRequest,
      com.jogit.server.gprc.RegisterReply> getRegisterMethod() {
    io.grpc.MethodDescriptor<com.jogit.server.gprc.RegisterRequest, com.jogit.server.gprc.RegisterReply> getRegisterMethod;
    if ((getRegisterMethod = RegisterGrpc.getRegisterMethod) == null) {
      synchronized (RegisterGrpc.class) {
        if ((getRegisterMethod = RegisterGrpc.getRegisterMethod) == null) {
          RegisterGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<com.jogit.server.gprc.RegisterRequest, com.jogit.server.gprc.RegisterReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Register", "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.gprc.RegisterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.gprc.RegisterReply.getDefaultInstance()))
                  .setSchemaDescriptor(new RegisterMethodDescriptorSupplier("Register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RegisterStub newStub(io.grpc.Channel channel) {
    return new RegisterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RegisterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RegisterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RegisterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RegisterFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static RegisterVertxStub newVertxStub(io.grpc.Channel channel) {
    return new RegisterVertxStub(channel);
  }

  /**
   * <pre>
   * WRTF
   * </pre>
   */
  public static abstract class RegisterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * hein
     * </pre>
     */
    public void register(com.jogit.server.gprc.RegisterRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.gprc.RegisterReply> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.jogit.server.gprc.RegisterRequest,
                com.jogit.server.gprc.RegisterReply>(
                  this, METHODID_REGISTER)))
          .build();
    }
  }

  /**
   * <pre>
   * WRTF
   * </pre>
   */
  public static final class RegisterStub extends io.grpc.stub.AbstractStub<RegisterStub> {
    public RegisterStub(io.grpc.Channel channel) {
      super(channel);
    }

    public RegisterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterStub(channel, callOptions);
    }

    /**
     * <pre>
     * hein
     * </pre>
     */
    public void register(com.jogit.server.gprc.RegisterRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.gprc.RegisterReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * WRTF
   * </pre>
   */
  public static final class RegisterBlockingStub extends io.grpc.stub.AbstractStub<RegisterBlockingStub> {
    public RegisterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public RegisterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * hein
     * </pre>
     */
    public com.jogit.server.gprc.RegisterReply register(com.jogit.server.gprc.RegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * WRTF
   * </pre>
   */
  public static final class RegisterFutureStub extends io.grpc.stub.AbstractStub<RegisterFutureStub> {
    public RegisterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public RegisterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * hein
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jogit.server.gprc.RegisterReply> register(
        com.jogit.server.gprc.RegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }
  }

  /**
   * <pre>
   * WRTF
   * </pre>
   */
  public static abstract class RegisterVertxImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * hein
     * </pre>
     */
    public void register(com.jogit.server.gprc.RegisterRequest request,
        io.vertx.core.Future<com.jogit.server.gprc.RegisterReply> response) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), RegisterGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.jogit.server.gprc.RegisterRequest,
                com.jogit.server.gprc.RegisterReply>(
                  this, METHODID_REGISTER)))
          .build();
    }
  }

  /**
   * <pre>
   * WRTF
   * </pre>
   */
  public static final class RegisterVertxStub extends io.grpc.stub.AbstractStub<RegisterVertxStub> {
    public RegisterVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public RegisterVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterVertxStub(channel, callOptions);
    }

    /**
     * <pre>
     * hein
     * </pre>
     */
    public void register(com.jogit.server.gprc.RegisterRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.jogit.server.gprc.RegisterReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, RegisterGrpc.toObserver(response));
    }
  }

  private static final int METHODID_REGISTER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RegisterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RegisterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((com.jogit.server.gprc.RegisterRequest) request,
              (io.grpc.stub.StreamObserver<com.jogit.server.gprc.RegisterReply>) responseObserver);
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
    private final RegisterVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(RegisterVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((com.jogit.server.gprc.RegisterRequest) request,
              (io.vertx.core.Future<com.jogit.server.gprc.RegisterReply>) io.vertx.core.Future.<com.jogit.server.gprc.RegisterReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.jogit.server.gprc.RegisterReply>) responseObserver).onNext(ar.result());
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

  private static abstract class RegisterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RegisterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.jogit.server.gprc.RegisterProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Register");
    }
  }

  private static final class RegisterFileDescriptorSupplier
      extends RegisterBaseDescriptorSupplier {
    RegisterFileDescriptorSupplier() {}
  }

  private static final class RegisterMethodDescriptorSupplier
      extends RegisterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RegisterMethodDescriptorSupplier(String methodName) {
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
      synchronized (RegisterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RegisterFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
