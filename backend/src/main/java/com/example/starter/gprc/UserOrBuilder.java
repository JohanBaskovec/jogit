// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.example.starter.gprc;

public interface UserOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helloworld.User)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string username = 1;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string password = 2;</code>
   * @return The password.
   */
  java.lang.String getPassword();
  /**
   * <code>string password = 2;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();

  /**
   * <code>string passwordSalt = 3;</code>
   * @return The passwordSalt.
   */
  java.lang.String getPasswordSalt();
  /**
   * <code>string passwordSalt = 3;</code>
   * @return The bytes for passwordSalt.
   */
  com.google.protobuf.ByteString
      getPasswordSaltBytes();
}
