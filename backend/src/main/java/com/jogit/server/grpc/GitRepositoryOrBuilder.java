// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: git-repository.proto

package com.jogit.server.grpc;

public interface GitRepositoryOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helloworld.GitRepository)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string userUserName = 2;</code>
   * @return The userUserName.
   */
  java.lang.String getUserUserName();
  /**
   * <code>string userUserName = 2;</code>
   * @return The bytes for userUserName.
   */
  com.google.protobuf.ByteString
      getUserUserNameBytes();

  /**
   * <code>.helloworld.User user = 3;</code>
   * @return Whether the user field is set.
   */
  boolean hasUser();
  /**
   * <code>.helloworld.User user = 3;</code>
   * @return The user.
   */
  com.jogit.server.grpc.User getUser();
  /**
   * <code>.helloworld.User user = 3;</code>
   */
  com.jogit.server.grpc.UserOrBuilder getUserOrBuilder();
}
