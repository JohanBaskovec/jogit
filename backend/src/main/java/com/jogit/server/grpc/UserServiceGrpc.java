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
    comments = "Source: user.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

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

  public static final String SERVICE_NAME = "helloworld.UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.jogit.server.grpc.GetSshPublicKeyRequest,
      com.jogit.server.grpc.GetSshPublicKeyReply> getGetSshPublicKeysMethod;

  public static io.grpc.MethodDescriptor<com.jogit.server.grpc.GetSshPublicKeyRequest,
      com.jogit.server.grpc.GetSshPublicKeyReply> getGetSshPublicKeysMethod() {
    io.grpc.MethodDescriptor<com.jogit.server.grpc.GetSshPublicKeyRequest, com.jogit.server.grpc.GetSshPublicKeyReply> getGetSshPublicKeysMethod;
    if ((getGetSshPublicKeysMethod = UserServiceGrpc.getGetSshPublicKeysMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetSshPublicKeysMethod = UserServiceGrpc.getGetSshPublicKeysMethod) == null) {
          UserServiceGrpc.getGetSshPublicKeysMethod = getGetSshPublicKeysMethod = 
              io.grpc.MethodDescriptor.<com.jogit.server.grpc.GetSshPublicKeyRequest, com.jogit.server.grpc.GetSshPublicKeyReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.UserService", "GetSshPublicKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.GetSshPublicKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.GetSshPublicKeyReply.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetSshPublicKeys"))
                  .build();
          }
        }
     }
     return getGetSshPublicKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.jogit.server.grpc.AddSshPublicKeyRequest,
      com.jogit.server.grpc.AddSshPublicKeyReply> getAddPublicSshKeyMethod;

  public static io.grpc.MethodDescriptor<com.jogit.server.grpc.AddSshPublicKeyRequest,
      com.jogit.server.grpc.AddSshPublicKeyReply> getAddPublicSshKeyMethod() {
    io.grpc.MethodDescriptor<com.jogit.server.grpc.AddSshPublicKeyRequest, com.jogit.server.grpc.AddSshPublicKeyReply> getAddPublicSshKeyMethod;
    if ((getAddPublicSshKeyMethod = UserServiceGrpc.getAddPublicSshKeyMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getAddPublicSshKeyMethod = UserServiceGrpc.getAddPublicSshKeyMethod) == null) {
          UserServiceGrpc.getAddPublicSshKeyMethod = getAddPublicSshKeyMethod = 
              io.grpc.MethodDescriptor.<com.jogit.server.grpc.AddSshPublicKeyRequest, com.jogit.server.grpc.AddSshPublicKeyReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.UserService", "AddPublicSshKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.AddSshPublicKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.AddSshPublicKeyReply.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("AddPublicSshKey"))
                  .build();
          }
        }
     }
     return getAddPublicSshKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.jogit.server.grpc.DeleteSshPublicKeyRequest,
      com.jogit.server.grpc.DeleteSshPublicKeyReply> getDeleteSshPublicKeyMethod;

  public static io.grpc.MethodDescriptor<com.jogit.server.grpc.DeleteSshPublicKeyRequest,
      com.jogit.server.grpc.DeleteSshPublicKeyReply> getDeleteSshPublicKeyMethod() {
    io.grpc.MethodDescriptor<com.jogit.server.grpc.DeleteSshPublicKeyRequest, com.jogit.server.grpc.DeleteSshPublicKeyReply> getDeleteSshPublicKeyMethod;
    if ((getDeleteSshPublicKeyMethod = UserServiceGrpc.getDeleteSshPublicKeyMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getDeleteSshPublicKeyMethod = UserServiceGrpc.getDeleteSshPublicKeyMethod) == null) {
          UserServiceGrpc.getDeleteSshPublicKeyMethod = getDeleteSshPublicKeyMethod = 
              io.grpc.MethodDescriptor.<com.jogit.server.grpc.DeleteSshPublicKeyRequest, com.jogit.server.grpc.DeleteSshPublicKeyReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.UserService", "DeleteSshPublicKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.DeleteSshPublicKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.DeleteSshPublicKeyReply.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("DeleteSshPublicKey"))
                  .build();
          }
        }
     }
     return getDeleteSshPublicKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.jogit.server.grpc.UpdateSshPublicKeyRequest,
      com.jogit.server.grpc.UpdateSshPublicKeyReply> getUpdateSshPublicKeyMethod;

  public static io.grpc.MethodDescriptor<com.jogit.server.grpc.UpdateSshPublicKeyRequest,
      com.jogit.server.grpc.UpdateSshPublicKeyReply> getUpdateSshPublicKeyMethod() {
    io.grpc.MethodDescriptor<com.jogit.server.grpc.UpdateSshPublicKeyRequest, com.jogit.server.grpc.UpdateSshPublicKeyReply> getUpdateSshPublicKeyMethod;
    if ((getUpdateSshPublicKeyMethod = UserServiceGrpc.getUpdateSshPublicKeyMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getUpdateSshPublicKeyMethod = UserServiceGrpc.getUpdateSshPublicKeyMethod) == null) {
          UserServiceGrpc.getUpdateSshPublicKeyMethod = getUpdateSshPublicKeyMethod = 
              io.grpc.MethodDescriptor.<com.jogit.server.grpc.UpdateSshPublicKeyRequest, com.jogit.server.grpc.UpdateSshPublicKeyReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.UserService", "UpdateSshPublicKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.UpdateSshPublicKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.jogit.server.grpc.UpdateSshPublicKeyReply.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("UpdateSshPublicKey"))
                  .build();
          }
        }
     }
     return getUpdateSshPublicKeyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static UserServiceVertxStub newVertxStub(io.grpc.Channel channel) {
    return new UserServiceVertxStub(channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSshPublicKeys(com.jogit.server.grpc.GetSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.GetSshPublicKeyReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSshPublicKeysMethod(), responseObserver);
    }

    /**
     */
    public void addPublicSshKey(com.jogit.server.grpc.AddSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.AddSshPublicKeyReply> responseObserver) {
      asyncUnimplementedUnaryCall(getAddPublicSshKeyMethod(), responseObserver);
    }

    /**
     */
    public void deleteSshPublicKey(com.jogit.server.grpc.DeleteSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.DeleteSshPublicKeyReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteSshPublicKeyMethod(), responseObserver);
    }

    /**
     */
    public void updateSshPublicKey(com.jogit.server.grpc.UpdateSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.UpdateSshPublicKeyReply> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateSshPublicKeyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSshPublicKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.jogit.server.grpc.GetSshPublicKeyRequest,
                com.jogit.server.grpc.GetSshPublicKeyReply>(
                  this, METHODID_GET_SSH_PUBLIC_KEYS)))
          .addMethod(
            getAddPublicSshKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.jogit.server.grpc.AddSshPublicKeyRequest,
                com.jogit.server.grpc.AddSshPublicKeyReply>(
                  this, METHODID_ADD_PUBLIC_SSH_KEY)))
          .addMethod(
            getDeleteSshPublicKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.jogit.server.grpc.DeleteSshPublicKeyRequest,
                com.jogit.server.grpc.DeleteSshPublicKeyReply>(
                  this, METHODID_DELETE_SSH_PUBLIC_KEY)))
          .addMethod(
            getUpdateSshPublicKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.jogit.server.grpc.UpdateSshPublicKeyRequest,
                com.jogit.server.grpc.UpdateSshPublicKeyReply>(
                  this, METHODID_UPDATE_SSH_PUBLIC_KEY)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub> {
    public UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    public UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSshPublicKeys(com.jogit.server.grpc.GetSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.GetSshPublicKeyReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSshPublicKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addPublicSshKey(com.jogit.server.grpc.AddSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.AddSshPublicKeyReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddPublicSshKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteSshPublicKey(com.jogit.server.grpc.DeleteSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.DeleteSshPublicKeyReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteSshPublicKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateSshPublicKey(com.jogit.server.grpc.UpdateSshPublicKeyRequest request,
        io.grpc.stub.StreamObserver<com.jogit.server.grpc.UpdateSshPublicKeyReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateSshPublicKeyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub> {
    public UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.jogit.server.grpc.GetSshPublicKeyReply getSshPublicKeys(com.jogit.server.grpc.GetSshPublicKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetSshPublicKeysMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.jogit.server.grpc.AddSshPublicKeyReply addPublicSshKey(com.jogit.server.grpc.AddSshPublicKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddPublicSshKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.jogit.server.grpc.DeleteSshPublicKeyReply deleteSshPublicKey(com.jogit.server.grpc.DeleteSshPublicKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteSshPublicKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.jogit.server.grpc.UpdateSshPublicKeyReply updateSshPublicKey(com.jogit.server.grpc.UpdateSshPublicKeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateSshPublicKeyMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub> {
    public UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jogit.server.grpc.GetSshPublicKeyReply> getSshPublicKeys(
        com.jogit.server.grpc.GetSshPublicKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSshPublicKeysMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jogit.server.grpc.AddSshPublicKeyReply> addPublicSshKey(
        com.jogit.server.grpc.AddSshPublicKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddPublicSshKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jogit.server.grpc.DeleteSshPublicKeyReply> deleteSshPublicKey(
        com.jogit.server.grpc.DeleteSshPublicKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteSshPublicKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jogit.server.grpc.UpdateSshPublicKeyReply> updateSshPublicKey(
        com.jogit.server.grpc.UpdateSshPublicKeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateSshPublicKeyMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class UserServiceVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSshPublicKeys(com.jogit.server.grpc.GetSshPublicKeyRequest request,
        io.vertx.core.Future<com.jogit.server.grpc.GetSshPublicKeyReply> response) {
      asyncUnimplementedUnaryCall(getGetSshPublicKeysMethod(), UserServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void addPublicSshKey(com.jogit.server.grpc.AddSshPublicKeyRequest request,
        io.vertx.core.Future<com.jogit.server.grpc.AddSshPublicKeyReply> response) {
      asyncUnimplementedUnaryCall(getAddPublicSshKeyMethod(), UserServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void deleteSshPublicKey(com.jogit.server.grpc.DeleteSshPublicKeyRequest request,
        io.vertx.core.Future<com.jogit.server.grpc.DeleteSshPublicKeyReply> response) {
      asyncUnimplementedUnaryCall(getDeleteSshPublicKeyMethod(), UserServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void updateSshPublicKey(com.jogit.server.grpc.UpdateSshPublicKeyRequest request,
        io.vertx.core.Future<com.jogit.server.grpc.UpdateSshPublicKeyReply> response) {
      asyncUnimplementedUnaryCall(getUpdateSshPublicKeyMethod(), UserServiceGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSshPublicKeysMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.jogit.server.grpc.GetSshPublicKeyRequest,
                com.jogit.server.grpc.GetSshPublicKeyReply>(
                  this, METHODID_GET_SSH_PUBLIC_KEYS)))
          .addMethod(
            getAddPublicSshKeyMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.jogit.server.grpc.AddSshPublicKeyRequest,
                com.jogit.server.grpc.AddSshPublicKeyReply>(
                  this, METHODID_ADD_PUBLIC_SSH_KEY)))
          .addMethod(
            getDeleteSshPublicKeyMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.jogit.server.grpc.DeleteSshPublicKeyRequest,
                com.jogit.server.grpc.DeleteSshPublicKeyReply>(
                  this, METHODID_DELETE_SSH_PUBLIC_KEY)))
          .addMethod(
            getUpdateSshPublicKeyMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.jogit.server.grpc.UpdateSshPublicKeyRequest,
                com.jogit.server.grpc.UpdateSshPublicKeyReply>(
                  this, METHODID_UPDATE_SSH_PUBLIC_KEY)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceVertxStub extends io.grpc.stub.AbstractStub<UserServiceVertxStub> {
    public UserServiceVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public UserServiceVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceVertxStub(channel, callOptions);
    }

    /**
     */
    public void getSshPublicKeys(com.jogit.server.grpc.GetSshPublicKeyRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.jogit.server.grpc.GetSshPublicKeyReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetSshPublicKeysMethod(), getCallOptions()), request, UserServiceGrpc.toObserver(response));
    }

    /**
     */
    public void addPublicSshKey(com.jogit.server.grpc.AddSshPublicKeyRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.jogit.server.grpc.AddSshPublicKeyReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getAddPublicSshKeyMethod(), getCallOptions()), request, UserServiceGrpc.toObserver(response));
    }

    /**
     */
    public void deleteSshPublicKey(com.jogit.server.grpc.DeleteSshPublicKeyRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.jogit.server.grpc.DeleteSshPublicKeyReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteSshPublicKeyMethod(), getCallOptions()), request, UserServiceGrpc.toObserver(response));
    }

    /**
     */
    public void updateSshPublicKey(com.jogit.server.grpc.UpdateSshPublicKeyRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.jogit.server.grpc.UpdateSshPublicKeyReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateSshPublicKeyMethod(), getCallOptions()), request, UserServiceGrpc.toObserver(response));
    }
  }

  private static final int METHODID_GET_SSH_PUBLIC_KEYS = 0;
  private static final int METHODID_ADD_PUBLIC_SSH_KEY = 1;
  private static final int METHODID_DELETE_SSH_PUBLIC_KEY = 2;
  private static final int METHODID_UPDATE_SSH_PUBLIC_KEY = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SSH_PUBLIC_KEYS:
          serviceImpl.getSshPublicKeys((com.jogit.server.grpc.GetSshPublicKeyRequest) request,
              (io.grpc.stub.StreamObserver<com.jogit.server.grpc.GetSshPublicKeyReply>) responseObserver);
          break;
        case METHODID_ADD_PUBLIC_SSH_KEY:
          serviceImpl.addPublicSshKey((com.jogit.server.grpc.AddSshPublicKeyRequest) request,
              (io.grpc.stub.StreamObserver<com.jogit.server.grpc.AddSshPublicKeyReply>) responseObserver);
          break;
        case METHODID_DELETE_SSH_PUBLIC_KEY:
          serviceImpl.deleteSshPublicKey((com.jogit.server.grpc.DeleteSshPublicKeyRequest) request,
              (io.grpc.stub.StreamObserver<com.jogit.server.grpc.DeleteSshPublicKeyReply>) responseObserver);
          break;
        case METHODID_UPDATE_SSH_PUBLIC_KEY:
          serviceImpl.updateSshPublicKey((com.jogit.server.grpc.UpdateSshPublicKeyRequest) request,
              (io.grpc.stub.StreamObserver<com.jogit.server.grpc.UpdateSshPublicKeyReply>) responseObserver);
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
    private final UserServiceVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(UserServiceVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SSH_PUBLIC_KEYS:
          serviceImpl.getSshPublicKeys((com.jogit.server.grpc.GetSshPublicKeyRequest) request,
              (io.vertx.core.Future<com.jogit.server.grpc.GetSshPublicKeyReply>) io.vertx.core.Future.<com.jogit.server.grpc.GetSshPublicKeyReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.jogit.server.grpc.GetSshPublicKeyReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_ADD_PUBLIC_SSH_KEY:
          serviceImpl.addPublicSshKey((com.jogit.server.grpc.AddSshPublicKeyRequest) request,
              (io.vertx.core.Future<com.jogit.server.grpc.AddSshPublicKeyReply>) io.vertx.core.Future.<com.jogit.server.grpc.AddSshPublicKeyReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.jogit.server.grpc.AddSshPublicKeyReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_DELETE_SSH_PUBLIC_KEY:
          serviceImpl.deleteSshPublicKey((com.jogit.server.grpc.DeleteSshPublicKeyRequest) request,
              (io.vertx.core.Future<com.jogit.server.grpc.DeleteSshPublicKeyReply>) io.vertx.core.Future.<com.jogit.server.grpc.DeleteSshPublicKeyReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.jogit.server.grpc.DeleteSshPublicKeyReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_UPDATE_SSH_PUBLIC_KEY:
          serviceImpl.updateSshPublicKey((com.jogit.server.grpc.UpdateSshPublicKeyRequest) request,
              (io.vertx.core.Future<com.jogit.server.grpc.UpdateSshPublicKeyReply>) io.vertx.core.Future.<com.jogit.server.grpc.UpdateSshPublicKeyReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.jogit.server.grpc.UpdateSshPublicKeyReply>) responseObserver).onNext(ar.result());
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

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.jogit.server.grpc.UserProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getGetSshPublicKeysMethod())
              .addMethod(getAddPublicSshKeyMethod())
              .addMethod(getDeleteSshPublicKeyMethod())
              .addMethod(getUpdateSshPublicKeyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
