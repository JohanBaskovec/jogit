// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.jogit.server.grpc;

public interface DeleteSshPublicKeyRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helloworld.DeleteSshPublicKeyRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string sessionToken = 1;</code>
   * @return The sessionToken.
   */
  java.lang.String getSessionToken();
  /**
   * <code>string sessionToken = 1;</code>
   * @return The bytes for sessionToken.
   */
  com.google.protobuf.ByteString
      getSessionTokenBytes();

  /**
   * <code>int32 sshPublicKeyId = 2;</code>
   * @return The sshPublicKeyId.
   */
  int getSshPublicKeyId();
}