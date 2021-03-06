// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: git-repository.proto

package com.jogit.server.grpc;

public final class GitRepositoryProto {
  private GitRepositoryProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_GitRepository_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_GitRepository_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_CreateGitRepositoryRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_CreateGitRepositoryRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_CreateGitRepositoryReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_CreateGitRepositoryReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_GetGitRepositoryOfUserRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_GetGitRepositoryOfUserRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_GetGitRepositoryOfUserReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_GetGitRepositoryOfUserReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_GetGitRepositoryDirectoryRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_GetGitRepositoryDirectoryRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_GetGitRepositoryDirectoryReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_GetGitRepositoryDirectoryReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_FileMetadata_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_FileMetadata_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024git-repository.proto\022\nhelloworld\032\nuser" +
      ".proto\"S\n\rGitRepository\022\014\n\004name\030\001 \001(\t\022\024\n" +
      "\014userUserName\030\002 \001(\t\022\036\n\004user\030\003 \001(\0132\020.hell" +
      "oworld.User\"@\n\032CreateGitRepositoryReques" +
      "t\022\014\n\004name\030\001 \001(\t\022\024\n\014sessionToken\030\002 \001(\t\"\032\n" +
      "\030CreateGitRepositoryReply\"G\n\035GetGitRepos" +
      "itoryOfUserRequest\022\020\n\010username\030\001 \001(\t\022\024\n\014" +
      "sessionToken\030\002 \001(\t\"Q\n\033GetGitRepositoryOf" +
      "UserReply\0222\n\017gitRepositories\030\001 \003(\0132\031.hel" +
      "loworld.GitRepository\"u\n GetGitRepositor" +
      "yDirectoryRequest\022\024\n\014sessionToken\030\001 \001(\t\022" +
      "\020\n\010username\030\002 \001(\t\022\022\n\nrepository\030\003 \001(\t\022\025\n" +
      "\rdirectoryPath\030\004 \001(\t\"\206\001\n\036GetGitRepositor" +
      "yDirectoryReply\022\020\n\010username\030\001 \001(\t\022\022\n\nrep" +
      "ository\030\002 \001(\t\022\025\n\rdirectoryPath\030\003 \001(\t\022\'\n\005" +
      "files\030\004 \003(\0132\030.helloworld.FileMetadata\"F\n" +
      "\014FileMetadata\022\014\n\004type\030\001 \001(\t\022\014\n\004sha1\030\002 \001(" +
      "\t\022\014\n\004size\030\003 \001(\003\022\014\n\004name\030\004 \001(\t2\277\002\n\024GitRep" +
      "ositoryService\022X\n\006Create\022&.helloworld.Cr" +
      "eateGitRepositoryRequest\032$.helloworld.Cr" +
      "eateGitRepositoryReply\"\000\022a\n\tGetOfUser\022)." +
      "helloworld.GetGitRepositoryOfUserRequest" +
      "\032\'.helloworld.GetGitRepositoryOfUserRepl" +
      "y\"\000\022j\n\014GetDirectory\022,.helloworld.GetGitR" +
      "epositoryDirectoryRequest\032*.helloworld.G" +
      "etGitRepositoryDirectoryReply\"\000B-\n\025com.j" +
      "ogit.server.grpcB\022GitRepositoryProtoP\001b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.jogit.server.grpc.UserProto.getDescriptor(),
        });
    internal_static_helloworld_GitRepository_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helloworld_GitRepository_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_GitRepository_descriptor,
        new java.lang.String[] { "Name", "UserUserName", "User", });
    internal_static_helloworld_CreateGitRepositoryRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helloworld_CreateGitRepositoryRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_CreateGitRepositoryRequest_descriptor,
        new java.lang.String[] { "Name", "SessionToken", });
    internal_static_helloworld_CreateGitRepositoryReply_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_helloworld_CreateGitRepositoryReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_CreateGitRepositoryReply_descriptor,
        new java.lang.String[] { });
    internal_static_helloworld_GetGitRepositoryOfUserRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_helloworld_GetGitRepositoryOfUserRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_GetGitRepositoryOfUserRequest_descriptor,
        new java.lang.String[] { "Username", "SessionToken", });
    internal_static_helloworld_GetGitRepositoryOfUserReply_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_helloworld_GetGitRepositoryOfUserReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_GetGitRepositoryOfUserReply_descriptor,
        new java.lang.String[] { "GitRepositories", });
    internal_static_helloworld_GetGitRepositoryDirectoryRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_helloworld_GetGitRepositoryDirectoryRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_GetGitRepositoryDirectoryRequest_descriptor,
        new java.lang.String[] { "SessionToken", "Username", "Repository", "DirectoryPath", });
    internal_static_helloworld_GetGitRepositoryDirectoryReply_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_helloworld_GetGitRepositoryDirectoryReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_GetGitRepositoryDirectoryReply_descriptor,
        new java.lang.String[] { "Username", "Repository", "DirectoryPath", "Files", });
    internal_static_helloworld_FileMetadata_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_helloworld_FileMetadata_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_FileMetadata_descriptor,
        new java.lang.String[] { "Type", "Sha1", "Size", "Name", });
    com.jogit.server.grpc.UserProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
