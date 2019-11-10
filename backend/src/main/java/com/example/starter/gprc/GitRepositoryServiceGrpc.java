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
    comments = "Source: git-repository.proto")
public final class GitRepositoryServiceGrpc {

  private GitRepositoryServiceGrpc() {}

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

  public static final String SERVICE_NAME = "helloworld.GitRepositoryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.starter.gprc.CreateGitRepositoryRequest,
      com.example.starter.gprc.CreateGitRepositoryReply> getCreateMethod;

  public static io.grpc.MethodDescriptor<com.example.starter.gprc.CreateGitRepositoryRequest,
      com.example.starter.gprc.CreateGitRepositoryReply> getCreateMethod() {
    io.grpc.MethodDescriptor<com.example.starter.gprc.CreateGitRepositoryRequest, com.example.starter.gprc.CreateGitRepositoryReply> getCreateMethod;
    if ((getCreateMethod = GitRepositoryServiceGrpc.getCreateMethod) == null) {
      synchronized (GitRepositoryServiceGrpc.class) {
        if ((getCreateMethod = GitRepositoryServiceGrpc.getCreateMethod) == null) {
          GitRepositoryServiceGrpc.getCreateMethod = getCreateMethod = 
              io.grpc.MethodDescriptor.<com.example.starter.gprc.CreateGitRepositoryRequest, com.example.starter.gprc.CreateGitRepositoryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GitRepositoryService", "Create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.starter.gprc.CreateGitRepositoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.starter.gprc.CreateGitRepositoryReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GitRepositoryServiceMethodDescriptorSupplier("Create"))
                  .build();
          }
        }
     }
     return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.starter.gprc.GetGitRepositoryOfUserRequest,
      com.example.starter.gprc.GetGitRepositoryOfUserReply> getGetOfUserMethod;

  public static io.grpc.MethodDescriptor<com.example.starter.gprc.GetGitRepositoryOfUserRequest,
      com.example.starter.gprc.GetGitRepositoryOfUserReply> getGetOfUserMethod() {
    io.grpc.MethodDescriptor<com.example.starter.gprc.GetGitRepositoryOfUserRequest, com.example.starter.gprc.GetGitRepositoryOfUserReply> getGetOfUserMethod;
    if ((getGetOfUserMethod = GitRepositoryServiceGrpc.getGetOfUserMethod) == null) {
      synchronized (GitRepositoryServiceGrpc.class) {
        if ((getGetOfUserMethod = GitRepositoryServiceGrpc.getGetOfUserMethod) == null) {
          GitRepositoryServiceGrpc.getGetOfUserMethod = getGetOfUserMethod = 
              io.grpc.MethodDescriptor.<com.example.starter.gprc.GetGitRepositoryOfUserRequest, com.example.starter.gprc.GetGitRepositoryOfUserReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GitRepositoryService", "GetOfUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.starter.gprc.GetGitRepositoryOfUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.starter.gprc.GetGitRepositoryOfUserReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GitRepositoryServiceMethodDescriptorSupplier("GetOfUser"))
                  .build();
          }
        }
     }
     return getGetOfUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GitRepositoryServiceStub newStub(io.grpc.Channel channel) {
    return new GitRepositoryServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GitRepositoryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GitRepositoryServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GitRepositoryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GitRepositoryServiceFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static GitRepositoryServiceVertxStub newVertxStub(io.grpc.Channel channel) {
    return new GitRepositoryServiceVertxStub(channel);
  }

  /**
   */
  public static abstract class GitRepositoryServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void create(com.example.starter.gprc.CreateGitRepositoryRequest request,
        io.grpc.stub.StreamObserver<com.example.starter.gprc.CreateGitRepositoryReply> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void getOfUser(com.example.starter.gprc.GetGitRepositoryOfUserRequest request,
        io.grpc.stub.StreamObserver<com.example.starter.gprc.GetGitRepositoryOfUserReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetOfUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.starter.gprc.CreateGitRepositoryRequest,
                com.example.starter.gprc.CreateGitRepositoryReply>(
                  this, METHODID_CREATE)))
          .addMethod(
            getGetOfUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.starter.gprc.GetGitRepositoryOfUserRequest,
                com.example.starter.gprc.GetGitRepositoryOfUserReply>(
                  this, METHODID_GET_OF_USER)))
          .build();
    }
  }

  /**
   */
  public static final class GitRepositoryServiceStub extends io.grpc.stub.AbstractStub<GitRepositoryServiceStub> {
    public GitRepositoryServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GitRepositoryServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GitRepositoryServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GitRepositoryServiceStub(channel, callOptions);
    }

    /**
     */
    public void create(com.example.starter.gprc.CreateGitRepositoryRequest request,
        io.grpc.stub.StreamObserver<com.example.starter.gprc.CreateGitRepositoryReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOfUser(com.example.starter.gprc.GetGitRepositoryOfUserRequest request,
        io.grpc.stub.StreamObserver<com.example.starter.gprc.GetGitRepositoryOfUserReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetOfUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GitRepositoryServiceBlockingStub extends io.grpc.stub.AbstractStub<GitRepositoryServiceBlockingStub> {
    public GitRepositoryServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GitRepositoryServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GitRepositoryServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GitRepositoryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.starter.gprc.CreateGitRepositoryReply create(com.example.starter.gprc.CreateGitRepositoryRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.starter.gprc.GetGitRepositoryOfUserReply getOfUser(com.example.starter.gprc.GetGitRepositoryOfUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetOfUserMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GitRepositoryServiceFutureStub extends io.grpc.stub.AbstractStub<GitRepositoryServiceFutureStub> {
    public GitRepositoryServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GitRepositoryServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GitRepositoryServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GitRepositoryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.starter.gprc.CreateGitRepositoryReply> create(
        com.example.starter.gprc.CreateGitRepositoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.starter.gprc.GetGitRepositoryOfUserReply> getOfUser(
        com.example.starter.gprc.GetGitRepositoryOfUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetOfUserMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class GitRepositoryServiceVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void create(com.example.starter.gprc.CreateGitRepositoryRequest request,
        io.vertx.core.Future<com.example.starter.gprc.CreateGitRepositoryReply> response) {
      asyncUnimplementedUnaryCall(getCreateMethod(), GitRepositoryServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getOfUser(com.example.starter.gprc.GetGitRepositoryOfUserRequest request,
        io.vertx.core.Future<com.example.starter.gprc.GetGitRepositoryOfUserReply> response) {
      asyncUnimplementedUnaryCall(getGetOfUserMethod(), GitRepositoryServiceGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.example.starter.gprc.CreateGitRepositoryRequest,
                com.example.starter.gprc.CreateGitRepositoryReply>(
                  this, METHODID_CREATE)))
          .addMethod(
            getGetOfUserMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.example.starter.gprc.GetGitRepositoryOfUserRequest,
                com.example.starter.gprc.GetGitRepositoryOfUserReply>(
                  this, METHODID_GET_OF_USER)))
          .build();
    }
  }

  /**
   */
  public static final class GitRepositoryServiceVertxStub extends io.grpc.stub.AbstractStub<GitRepositoryServiceVertxStub> {
    public GitRepositoryServiceVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GitRepositoryServiceVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GitRepositoryServiceVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GitRepositoryServiceVertxStub(channel, callOptions);
    }

    /**
     */
    public void create(com.example.starter.gprc.CreateGitRepositoryRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.example.starter.gprc.CreateGitRepositoryReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, GitRepositoryServiceGrpc.toObserver(response));
    }

    /**
     */
    public void getOfUser(com.example.starter.gprc.GetGitRepositoryOfUserRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.example.starter.gprc.GetGitRepositoryOfUserReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetOfUserMethod(), getCallOptions()), request, GitRepositoryServiceGrpc.toObserver(response));
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_GET_OF_USER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GitRepositoryServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GitRepositoryServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((com.example.starter.gprc.CreateGitRepositoryRequest) request,
              (io.grpc.stub.StreamObserver<com.example.starter.gprc.CreateGitRepositoryReply>) responseObserver);
          break;
        case METHODID_GET_OF_USER:
          serviceImpl.getOfUser((com.example.starter.gprc.GetGitRepositoryOfUserRequest) request,
              (io.grpc.stub.StreamObserver<com.example.starter.gprc.GetGitRepositoryOfUserReply>) responseObserver);
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
    private final GitRepositoryServiceVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(GitRepositoryServiceVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((com.example.starter.gprc.CreateGitRepositoryRequest) request,
              (io.vertx.core.Future<com.example.starter.gprc.CreateGitRepositoryReply>) io.vertx.core.Future.<com.example.starter.gprc.CreateGitRepositoryReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.example.starter.gprc.CreateGitRepositoryReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_OF_USER:
          serviceImpl.getOfUser((com.example.starter.gprc.GetGitRepositoryOfUserRequest) request,
              (io.vertx.core.Future<com.example.starter.gprc.GetGitRepositoryOfUserReply>) io.vertx.core.Future.<com.example.starter.gprc.GetGitRepositoryOfUserReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.example.starter.gprc.GetGitRepositoryOfUserReply>) responseObserver).onNext(ar.result());
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

  private static abstract class GitRepositoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GitRepositoryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.starter.gprc.GitRepositoryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GitRepositoryService");
    }
  }

  private static final class GitRepositoryServiceFileDescriptorSupplier
      extends GitRepositoryServiceBaseDescriptorSupplier {
    GitRepositoryServiceFileDescriptorSupplier() {}
  }

  private static final class GitRepositoryServiceMethodDescriptorSupplier
      extends GitRepositoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GitRepositoryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GitRepositoryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GitRepositoryServiceFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getGetOfUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
