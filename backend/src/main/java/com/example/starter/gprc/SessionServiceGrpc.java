package com.example.starter.gprc;

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
    comments = "Source: session.proto")
public final class SessionServiceGrpc {

  private SessionServiceGrpc() {}

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

  public static final String SERVICE_NAME = "helloworld.SessionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.starter.gprc.GetCurrentSessionRequest,
      com.example.starter.gprc.GetCurrentSessionReply> getGetCurrentSessionMethod;

  public static io.grpc.MethodDescriptor<com.example.starter.gprc.GetCurrentSessionRequest,
      com.example.starter.gprc.GetCurrentSessionReply> getGetCurrentSessionMethod() {
    io.grpc.MethodDescriptor<com.example.starter.gprc.GetCurrentSessionRequest, com.example.starter.gprc.GetCurrentSessionReply> getGetCurrentSessionMethod;
    if ((getGetCurrentSessionMethod = SessionServiceGrpc.getGetCurrentSessionMethod) == null) {
      synchronized (SessionServiceGrpc.class) {
        if ((getGetCurrentSessionMethod = SessionServiceGrpc.getGetCurrentSessionMethod) == null) {
          SessionServiceGrpc.getGetCurrentSessionMethod = getGetCurrentSessionMethod = 
              io.grpc.MethodDescriptor.<com.example.starter.gprc.GetCurrentSessionRequest, com.example.starter.gprc.GetCurrentSessionReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.SessionService", "getCurrentSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.starter.gprc.GetCurrentSessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.starter.gprc.GetCurrentSessionReply.getDefaultInstance()))
                  .setSchemaDescriptor(new SessionServiceMethodDescriptorSupplier("getCurrentSession"))
                  .build();
          }
        }
     }
     return getGetCurrentSessionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SessionServiceStub newStub(io.grpc.Channel channel) {
    return new SessionServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SessionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SessionServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SessionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SessionServiceFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static SessionServiceVertxStub newVertxStub(io.grpc.Channel channel) {
    return new SessionServiceVertxStub(channel);
  }

  /**
   */
  public static abstract class SessionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCurrentSession(com.example.starter.gprc.GetCurrentSessionRequest request,
        io.grpc.stub.StreamObserver<com.example.starter.gprc.GetCurrentSessionReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCurrentSessionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCurrentSessionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.starter.gprc.GetCurrentSessionRequest,
                com.example.starter.gprc.GetCurrentSessionReply>(
                  this, METHODID_GET_CURRENT_SESSION)))
          .build();
    }
  }

  /**
   */
  public static final class SessionServiceStub extends io.grpc.stub.AbstractStub<SessionServiceStub> {
    public SessionServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    public SessionServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SessionServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SessionServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCurrentSession(com.example.starter.gprc.GetCurrentSessionRequest request,
        io.grpc.stub.StreamObserver<com.example.starter.gprc.GetCurrentSessionReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCurrentSessionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SessionServiceBlockingStub extends io.grpc.stub.AbstractStub<SessionServiceBlockingStub> {
    public SessionServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public SessionServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SessionServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SessionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.starter.gprc.GetCurrentSessionReply getCurrentSession(com.example.starter.gprc.GetCurrentSessionRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetCurrentSessionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SessionServiceFutureStub extends io.grpc.stub.AbstractStub<SessionServiceFutureStub> {
    public SessionServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public SessionServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SessionServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SessionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.starter.gprc.GetCurrentSessionReply> getCurrentSession(
        com.example.starter.gprc.GetCurrentSessionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCurrentSessionMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class SessionServiceVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCurrentSession(com.example.starter.gprc.GetCurrentSessionRequest request,
        io.vertx.core.Future<com.example.starter.gprc.GetCurrentSessionReply> response) {
      asyncUnimplementedUnaryCall(getGetCurrentSessionMethod(), SessionServiceGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCurrentSessionMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.example.starter.gprc.GetCurrentSessionRequest,
                com.example.starter.gprc.GetCurrentSessionReply>(
                  this, METHODID_GET_CURRENT_SESSION)))
          .build();
    }
  }

  /**
   */
  public static final class SessionServiceVertxStub extends io.grpc.stub.AbstractStub<SessionServiceVertxStub> {
    public SessionServiceVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public SessionServiceVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SessionServiceVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SessionServiceVertxStub(channel, callOptions);
    }

    /**
     */
    public void getCurrentSession(com.example.starter.gprc.GetCurrentSessionRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.example.starter.gprc.GetCurrentSessionReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetCurrentSessionMethod(), getCallOptions()), request, SessionServiceGrpc.toObserver(response));
    }
  }

  private static final int METHODID_GET_CURRENT_SESSION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SessionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SessionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CURRENT_SESSION:
          serviceImpl.getCurrentSession((com.example.starter.gprc.GetCurrentSessionRequest) request,
              (io.grpc.stub.StreamObserver<com.example.starter.gprc.GetCurrentSessionReply>) responseObserver);
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
    private final SessionServiceVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(SessionServiceVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CURRENT_SESSION:
          serviceImpl.getCurrentSession((com.example.starter.gprc.GetCurrentSessionRequest) request,
              (io.vertx.core.Future<com.example.starter.gprc.GetCurrentSessionReply>) io.vertx.core.Future.<com.example.starter.gprc.GetCurrentSessionReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.example.starter.gprc.GetCurrentSessionReply>) responseObserver).onNext(ar.result());
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

  private static abstract class SessionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SessionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.starter.gprc.SessionProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SessionService");
    }
  }

  private static final class SessionServiceFileDescriptorSupplier
      extends SessionServiceBaseDescriptorSupplier {
    SessionServiceFileDescriptorSupplier() {}
  }

  private static final class SessionServiceMethodDescriptorSupplier
      extends SessionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SessionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SessionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SessionServiceFileDescriptorSupplier())
              .addMethod(getGetCurrentSessionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
