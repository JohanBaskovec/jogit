// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: login.proto

package com.example.starter.gprc;

public final class LoginProto {
  private LoginProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_LoginRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_LoginRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_LoginReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_LoginReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013login.proto\022\nhelloworld\032\rsession.proto" +
      "\"H\n\014LoginRequest\022\020\n\010username\030\001 \001(\t\022\020\n\010pa" +
      "ssword\030\002 \001(\t\022\024\n\014sessionToken\030\003 \001(\t\"2\n\nLo" +
      "ginReply\022$\n\007session\030\001 \001(\0132\023.helloworld.S" +
      "ession2D\n\005Login\022;\n\005Login\022\030.helloworld.Lo" +
      "ginRequest\032\026.helloworld.LoginReply\"\000B(\n\030" +
      "com.example.starter.gprcB\nLoginProtoP\001b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.example.starter.gprc.SessionProto.getDescriptor(),
        });
    internal_static_helloworld_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helloworld_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_LoginRequest_descriptor,
        new java.lang.String[] { "Username", "Password", "SessionToken", });
    internal_static_helloworld_LoginReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helloworld_LoginReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_LoginReply_descriptor,
        new java.lang.String[] { "Session", });
    com.example.starter.gprc.SessionProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
