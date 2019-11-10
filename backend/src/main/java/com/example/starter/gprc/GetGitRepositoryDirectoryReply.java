// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: git-repository.proto

package com.example.starter.gprc;

/**
 * Protobuf type {@code helloworld.GetGitRepositoryDirectoryReply}
 */
public  final class GetGitRepositoryDirectoryReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helloworld.GetGitRepositoryDirectoryReply)
    GetGitRepositoryDirectoryReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetGitRepositoryDirectoryReply.newBuilder() to construct.
  private GetGitRepositoryDirectoryReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetGitRepositoryDirectoryReply() {
    username_ = "";
    repository_ = "";
    directoryPath_ = "";
    files_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetGitRepositoryDirectoryReply();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetGitRepositoryDirectoryReply(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            username_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            repository_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            directoryPath_ = s;
            break;
          }
          case 34: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              files_ = new java.util.ArrayList<com.example.starter.gprc.FileMetadata>();
              mutable_bitField0_ |= 0x00000001;
            }
            files_.add(
                input.readMessage(com.example.starter.gprc.FileMetadata.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        files_ = java.util.Collections.unmodifiableList(files_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.starter.gprc.GitRepositoryProto.internal_static_helloworld_GetGitRepositoryDirectoryReply_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.starter.gprc.GitRepositoryProto.internal_static_helloworld_GetGitRepositoryDirectoryReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.starter.gprc.GetGitRepositoryDirectoryReply.class, com.example.starter.gprc.GetGitRepositoryDirectoryReply.Builder.class);
  }

  public static final int USERNAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object username_;
  /**
   * <code>string username = 1;</code>
   * @return The username.
   */
  public java.lang.String getUsername() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      username_ = s;
      return s;
    }
  }
  /**
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  public com.google.protobuf.ByteString
      getUsernameBytes() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      username_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REPOSITORY_FIELD_NUMBER = 2;
  private volatile java.lang.Object repository_;
  /**
   * <code>string repository = 2;</code>
   * @return The repository.
   */
  public java.lang.String getRepository() {
    java.lang.Object ref = repository_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      repository_ = s;
      return s;
    }
  }
  /**
   * <code>string repository = 2;</code>
   * @return The bytes for repository.
   */
  public com.google.protobuf.ByteString
      getRepositoryBytes() {
    java.lang.Object ref = repository_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      repository_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DIRECTORYPATH_FIELD_NUMBER = 3;
  private volatile java.lang.Object directoryPath_;
  /**
   * <code>string directoryPath = 3;</code>
   * @return The directoryPath.
   */
  public java.lang.String getDirectoryPath() {
    java.lang.Object ref = directoryPath_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      directoryPath_ = s;
      return s;
    }
  }
  /**
   * <code>string directoryPath = 3;</code>
   * @return The bytes for directoryPath.
   */
  public com.google.protobuf.ByteString
      getDirectoryPathBytes() {
    java.lang.Object ref = directoryPath_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      directoryPath_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FILES_FIELD_NUMBER = 4;
  private java.util.List<com.example.starter.gprc.FileMetadata> files_;
  /**
   * <code>repeated .helloworld.FileMetadata files = 4;</code>
   */
  public java.util.List<com.example.starter.gprc.FileMetadata> getFilesList() {
    return files_;
  }
  /**
   * <code>repeated .helloworld.FileMetadata files = 4;</code>
   */
  public java.util.List<? extends com.example.starter.gprc.FileMetadataOrBuilder> 
      getFilesOrBuilderList() {
    return files_;
  }
  /**
   * <code>repeated .helloworld.FileMetadata files = 4;</code>
   */
  public int getFilesCount() {
    return files_.size();
  }
  /**
   * <code>repeated .helloworld.FileMetadata files = 4;</code>
   */
  public com.example.starter.gprc.FileMetadata getFiles(int index) {
    return files_.get(index);
  }
  /**
   * <code>repeated .helloworld.FileMetadata files = 4;</code>
   */
  public com.example.starter.gprc.FileMetadataOrBuilder getFilesOrBuilder(
      int index) {
    return files_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getUsernameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, username_);
    }
    if (!getRepositoryBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, repository_);
    }
    if (!getDirectoryPathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, directoryPath_);
    }
    for (int i = 0; i < files_.size(); i++) {
      output.writeMessage(4, files_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getUsernameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, username_);
    }
    if (!getRepositoryBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, repository_);
    }
    if (!getDirectoryPathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, directoryPath_);
    }
    for (int i = 0; i < files_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, files_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.starter.gprc.GetGitRepositoryDirectoryReply)) {
      return super.equals(obj);
    }
    com.example.starter.gprc.GetGitRepositoryDirectoryReply other = (com.example.starter.gprc.GetGitRepositoryDirectoryReply) obj;

    if (!getUsername()
        .equals(other.getUsername())) return false;
    if (!getRepository()
        .equals(other.getRepository())) return false;
    if (!getDirectoryPath()
        .equals(other.getDirectoryPath())) return false;
    if (!getFilesList()
        .equals(other.getFilesList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + USERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getUsername().hashCode();
    hash = (37 * hash) + REPOSITORY_FIELD_NUMBER;
    hash = (53 * hash) + getRepository().hashCode();
    hash = (37 * hash) + DIRECTORYPATH_FIELD_NUMBER;
    hash = (53 * hash) + getDirectoryPath().hashCode();
    if (getFilesCount() > 0) {
      hash = (37 * hash) + FILES_FIELD_NUMBER;
      hash = (53 * hash) + getFilesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.starter.gprc.GetGitRepositoryDirectoryReply prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code helloworld.GetGitRepositoryDirectoryReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helloworld.GetGitRepositoryDirectoryReply)
      com.example.starter.gprc.GetGitRepositoryDirectoryReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.starter.gprc.GitRepositoryProto.internal_static_helloworld_GetGitRepositoryDirectoryReply_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.starter.gprc.GitRepositoryProto.internal_static_helloworld_GetGitRepositoryDirectoryReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.starter.gprc.GetGitRepositoryDirectoryReply.class, com.example.starter.gprc.GetGitRepositoryDirectoryReply.Builder.class);
    }

    // Construct using com.example.starter.gprc.GetGitRepositoryDirectoryReply.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getFilesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      username_ = "";

      repository_ = "";

      directoryPath_ = "";

      if (filesBuilder_ == null) {
        files_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        filesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.starter.gprc.GitRepositoryProto.internal_static_helloworld_GetGitRepositoryDirectoryReply_descriptor;
    }

    @java.lang.Override
    public com.example.starter.gprc.GetGitRepositoryDirectoryReply getDefaultInstanceForType() {
      return com.example.starter.gprc.GetGitRepositoryDirectoryReply.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.starter.gprc.GetGitRepositoryDirectoryReply build() {
      com.example.starter.gprc.GetGitRepositoryDirectoryReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.starter.gprc.GetGitRepositoryDirectoryReply buildPartial() {
      com.example.starter.gprc.GetGitRepositoryDirectoryReply result = new com.example.starter.gprc.GetGitRepositoryDirectoryReply(this);
      int from_bitField0_ = bitField0_;
      result.username_ = username_;
      result.repository_ = repository_;
      result.directoryPath_ = directoryPath_;
      if (filesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          files_ = java.util.Collections.unmodifiableList(files_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.files_ = files_;
      } else {
        result.files_ = filesBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.starter.gprc.GetGitRepositoryDirectoryReply) {
        return mergeFrom((com.example.starter.gprc.GetGitRepositoryDirectoryReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.starter.gprc.GetGitRepositoryDirectoryReply other) {
      if (other == com.example.starter.gprc.GetGitRepositoryDirectoryReply.getDefaultInstance()) return this;
      if (!other.getUsername().isEmpty()) {
        username_ = other.username_;
        onChanged();
      }
      if (!other.getRepository().isEmpty()) {
        repository_ = other.repository_;
        onChanged();
      }
      if (!other.getDirectoryPath().isEmpty()) {
        directoryPath_ = other.directoryPath_;
        onChanged();
      }
      if (filesBuilder_ == null) {
        if (!other.files_.isEmpty()) {
          if (files_.isEmpty()) {
            files_ = other.files_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureFilesIsMutable();
            files_.addAll(other.files_);
          }
          onChanged();
        }
      } else {
        if (!other.files_.isEmpty()) {
          if (filesBuilder_.isEmpty()) {
            filesBuilder_.dispose();
            filesBuilder_ = null;
            files_ = other.files_;
            bitField0_ = (bitField0_ & ~0x00000001);
            filesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getFilesFieldBuilder() : null;
          } else {
            filesBuilder_.addAllMessages(other.files_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.example.starter.gprc.GetGitRepositoryDirectoryReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.starter.gprc.GetGitRepositoryDirectoryReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object username_ = "";
    /**
     * <code>string username = 1;</code>
     * @return The username.
     */
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        username_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string username = 1;</code>
     * @return The bytes for username.
     */
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string username = 1;</code>
     * @param value The username to set.
     * @return This builder for chaining.
     */
    public Builder setUsername(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      username_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string username = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUsername() {
      
      username_ = getDefaultInstance().getUsername();
      onChanged();
      return this;
    }
    /**
     * <code>string username = 1;</code>
     * @param value The bytes for username to set.
     * @return This builder for chaining.
     */
    public Builder setUsernameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      username_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object repository_ = "";
    /**
     * <code>string repository = 2;</code>
     * @return The repository.
     */
    public java.lang.String getRepository() {
      java.lang.Object ref = repository_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        repository_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string repository = 2;</code>
     * @return The bytes for repository.
     */
    public com.google.protobuf.ByteString
        getRepositoryBytes() {
      java.lang.Object ref = repository_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        repository_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string repository = 2;</code>
     * @param value The repository to set.
     * @return This builder for chaining.
     */
    public Builder setRepository(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      repository_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string repository = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearRepository() {
      
      repository_ = getDefaultInstance().getRepository();
      onChanged();
      return this;
    }
    /**
     * <code>string repository = 2;</code>
     * @param value The bytes for repository to set.
     * @return This builder for chaining.
     */
    public Builder setRepositoryBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      repository_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object directoryPath_ = "";
    /**
     * <code>string directoryPath = 3;</code>
     * @return The directoryPath.
     */
    public java.lang.String getDirectoryPath() {
      java.lang.Object ref = directoryPath_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        directoryPath_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string directoryPath = 3;</code>
     * @return The bytes for directoryPath.
     */
    public com.google.protobuf.ByteString
        getDirectoryPathBytes() {
      java.lang.Object ref = directoryPath_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        directoryPath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string directoryPath = 3;</code>
     * @param value The directoryPath to set.
     * @return This builder for chaining.
     */
    public Builder setDirectoryPath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      directoryPath_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string directoryPath = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearDirectoryPath() {
      
      directoryPath_ = getDefaultInstance().getDirectoryPath();
      onChanged();
      return this;
    }
    /**
     * <code>string directoryPath = 3;</code>
     * @param value The bytes for directoryPath to set.
     * @return This builder for chaining.
     */
    public Builder setDirectoryPathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      directoryPath_ = value;
      onChanged();
      return this;
    }

    private java.util.List<com.example.starter.gprc.FileMetadata> files_ =
      java.util.Collections.emptyList();
    private void ensureFilesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        files_ = new java.util.ArrayList<com.example.starter.gprc.FileMetadata>(files_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.example.starter.gprc.FileMetadata, com.example.starter.gprc.FileMetadata.Builder, com.example.starter.gprc.FileMetadataOrBuilder> filesBuilder_;

    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public java.util.List<com.example.starter.gprc.FileMetadata> getFilesList() {
      if (filesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(files_);
      } else {
        return filesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public int getFilesCount() {
      if (filesBuilder_ == null) {
        return files_.size();
      } else {
        return filesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public com.example.starter.gprc.FileMetadata getFiles(int index) {
      if (filesBuilder_ == null) {
        return files_.get(index);
      } else {
        return filesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder setFiles(
        int index, com.example.starter.gprc.FileMetadata value) {
      if (filesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFilesIsMutable();
        files_.set(index, value);
        onChanged();
      } else {
        filesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder setFiles(
        int index, com.example.starter.gprc.FileMetadata.Builder builderForValue) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.set(index, builderForValue.build());
        onChanged();
      } else {
        filesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder addFiles(com.example.starter.gprc.FileMetadata value) {
      if (filesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFilesIsMutable();
        files_.add(value);
        onChanged();
      } else {
        filesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder addFiles(
        int index, com.example.starter.gprc.FileMetadata value) {
      if (filesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFilesIsMutable();
        files_.add(index, value);
        onChanged();
      } else {
        filesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder addFiles(
        com.example.starter.gprc.FileMetadata.Builder builderForValue) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.add(builderForValue.build());
        onChanged();
      } else {
        filesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder addFiles(
        int index, com.example.starter.gprc.FileMetadata.Builder builderForValue) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.add(index, builderForValue.build());
        onChanged();
      } else {
        filesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder addAllFiles(
        java.lang.Iterable<? extends com.example.starter.gprc.FileMetadata> values) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, files_);
        onChanged();
      } else {
        filesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder clearFiles() {
      if (filesBuilder_ == null) {
        files_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        filesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public Builder removeFiles(int index) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.remove(index);
        onChanged();
      } else {
        filesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public com.example.starter.gprc.FileMetadata.Builder getFilesBuilder(
        int index) {
      return getFilesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public com.example.starter.gprc.FileMetadataOrBuilder getFilesOrBuilder(
        int index) {
      if (filesBuilder_ == null) {
        return files_.get(index);  } else {
        return filesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public java.util.List<? extends com.example.starter.gprc.FileMetadataOrBuilder> 
         getFilesOrBuilderList() {
      if (filesBuilder_ != null) {
        return filesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(files_);
      }
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public com.example.starter.gprc.FileMetadata.Builder addFilesBuilder() {
      return getFilesFieldBuilder().addBuilder(
          com.example.starter.gprc.FileMetadata.getDefaultInstance());
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public com.example.starter.gprc.FileMetadata.Builder addFilesBuilder(
        int index) {
      return getFilesFieldBuilder().addBuilder(
          index, com.example.starter.gprc.FileMetadata.getDefaultInstance());
    }
    /**
     * <code>repeated .helloworld.FileMetadata files = 4;</code>
     */
    public java.util.List<com.example.starter.gprc.FileMetadata.Builder> 
         getFilesBuilderList() {
      return getFilesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.example.starter.gprc.FileMetadata, com.example.starter.gprc.FileMetadata.Builder, com.example.starter.gprc.FileMetadataOrBuilder> 
        getFilesFieldBuilder() {
      if (filesBuilder_ == null) {
        filesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.example.starter.gprc.FileMetadata, com.example.starter.gprc.FileMetadata.Builder, com.example.starter.gprc.FileMetadataOrBuilder>(
                files_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        files_ = null;
      }
      return filesBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:helloworld.GetGitRepositoryDirectoryReply)
  }

  // @@protoc_insertion_point(class_scope:helloworld.GetGitRepositoryDirectoryReply)
  private static final com.example.starter.gprc.GetGitRepositoryDirectoryReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.starter.gprc.GetGitRepositoryDirectoryReply();
  }

  public static com.example.starter.gprc.GetGitRepositoryDirectoryReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetGitRepositoryDirectoryReply>
      PARSER = new com.google.protobuf.AbstractParser<GetGitRepositoryDirectoryReply>() {
    @java.lang.Override
    public GetGitRepositoryDirectoryReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetGitRepositoryDirectoryReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetGitRepositoryDirectoryReply> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetGitRepositoryDirectoryReply> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.starter.gprc.GetGitRepositoryDirectoryReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

