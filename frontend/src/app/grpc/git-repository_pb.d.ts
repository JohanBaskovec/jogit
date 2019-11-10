import * as jspb from "google-protobuf"

import * as user_pb from './user_pb';

export class GitRepository extends jspb.Message {
  getName(): string;
  setName(value: string): void;

  getUserusername(): string;
  setUserusername(value: string): void;

  getUser(): user_pb.User | undefined;
  setUser(value?: user_pb.User): void;
  hasUser(): boolean;
  clearUser(): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GitRepository.AsObject;
  static toObject(includeInstance: boolean, msg: GitRepository): GitRepository.AsObject;
  static serializeBinaryToWriter(message: GitRepository, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GitRepository;
  static deserializeBinaryFromReader(message: GitRepository, reader: jspb.BinaryReader): GitRepository;
}

export namespace GitRepository {
  export type AsObject = {
    name: string,
    userusername: string,
    user?: user_pb.User.AsObject,
  }
}

export class CreateGitRepositoryRequest extends jspb.Message {
  getName(): string;
  setName(value: string): void;

  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateGitRepositoryRequest.AsObject;
  static toObject(includeInstance: boolean, msg: CreateGitRepositoryRequest): CreateGitRepositoryRequest.AsObject;
  static serializeBinaryToWriter(message: CreateGitRepositoryRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateGitRepositoryRequest;
  static deserializeBinaryFromReader(message: CreateGitRepositoryRequest, reader: jspb.BinaryReader): CreateGitRepositoryRequest;
}

export namespace CreateGitRepositoryRequest {
  export type AsObject = {
    name: string,
    sessiontoken: string,
  }
}

export class CreateGitRepositoryReply extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): CreateGitRepositoryReply.AsObject;
  static toObject(includeInstance: boolean, msg: CreateGitRepositoryReply): CreateGitRepositoryReply.AsObject;
  static serializeBinaryToWriter(message: CreateGitRepositoryReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): CreateGitRepositoryReply;
  static deserializeBinaryFromReader(message: CreateGitRepositoryReply, reader: jspb.BinaryReader): CreateGitRepositoryReply;
}

export namespace CreateGitRepositoryReply {
  export type AsObject = {
  }
}

export class GetGitRepositoryOfUserRequest extends jspb.Message {
  getUsername(): string;
  setUsername(value: string): void;

  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetGitRepositoryOfUserRequest.AsObject;
  static toObject(includeInstance: boolean, msg: GetGitRepositoryOfUserRequest): GetGitRepositoryOfUserRequest.AsObject;
  static serializeBinaryToWriter(message: GetGitRepositoryOfUserRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetGitRepositoryOfUserRequest;
  static deserializeBinaryFromReader(message: GetGitRepositoryOfUserRequest, reader: jspb.BinaryReader): GetGitRepositoryOfUserRequest;
}

export namespace GetGitRepositoryOfUserRequest {
  export type AsObject = {
    username: string,
    sessiontoken: string,
  }
}

export class GetGitRepositoryOfUserReply extends jspb.Message {
  getGitrepositoriesList(): Array<GitRepository>;
  setGitrepositoriesList(value: Array<GitRepository>): void;
  clearGitrepositoriesList(): void;
  addGitrepositories(value?: GitRepository, index?: number): GitRepository;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetGitRepositoryOfUserReply.AsObject;
  static toObject(includeInstance: boolean, msg: GetGitRepositoryOfUserReply): GetGitRepositoryOfUserReply.AsObject;
  static serializeBinaryToWriter(message: GetGitRepositoryOfUserReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetGitRepositoryOfUserReply;
  static deserializeBinaryFromReader(message: GetGitRepositoryOfUserReply, reader: jspb.BinaryReader): GetGitRepositoryOfUserReply;
}

export namespace GetGitRepositoryOfUserReply {
  export type AsObject = {
    gitrepositoriesList: Array<GitRepository.AsObject>,
  }
}

export class GetGitRepositoryDirectoryRequest extends jspb.Message {
  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  getUsername(): string;
  setUsername(value: string): void;

  getRepository(): string;
  setRepository(value: string): void;

  getDirectorypath(): string;
  setDirectorypath(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetGitRepositoryDirectoryRequest.AsObject;
  static toObject(includeInstance: boolean, msg: GetGitRepositoryDirectoryRequest): GetGitRepositoryDirectoryRequest.AsObject;
  static serializeBinaryToWriter(message: GetGitRepositoryDirectoryRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetGitRepositoryDirectoryRequest;
  static deserializeBinaryFromReader(message: GetGitRepositoryDirectoryRequest, reader: jspb.BinaryReader): GetGitRepositoryDirectoryRequest;
}

export namespace GetGitRepositoryDirectoryRequest {
  export type AsObject = {
    sessiontoken: string,
    username: string,
    repository: string,
    directorypath: string,
  }
}

export class GetGitRepositoryDirectoryReply extends jspb.Message {
  getUsername(): string;
  setUsername(value: string): void;

  getRepository(): string;
  setRepository(value: string): void;

  getDirectorypath(): string;
  setDirectorypath(value: string): void;

  getFilesList(): Array<FileMetadata>;
  setFilesList(value: Array<FileMetadata>): void;
  clearFilesList(): void;
  addFiles(value?: FileMetadata, index?: number): FileMetadata;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetGitRepositoryDirectoryReply.AsObject;
  static toObject(includeInstance: boolean, msg: GetGitRepositoryDirectoryReply): GetGitRepositoryDirectoryReply.AsObject;
  static serializeBinaryToWriter(message: GetGitRepositoryDirectoryReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetGitRepositoryDirectoryReply;
  static deserializeBinaryFromReader(message: GetGitRepositoryDirectoryReply, reader: jspb.BinaryReader): GetGitRepositoryDirectoryReply;
}

export namespace GetGitRepositoryDirectoryReply {
  export type AsObject = {
    username: string,
    repository: string,
    directorypath: string,
    filesList: Array<FileMetadata.AsObject>,
  }
}

export class FileMetadata extends jspb.Message {
  getType(): string;
  setType(value: string): void;

  getSha1(): string;
  setSha1(value: string): void;

  getSize(): number;
  setSize(value: number): void;

  getName(): string;
  setName(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): FileMetadata.AsObject;
  static toObject(includeInstance: boolean, msg: FileMetadata): FileMetadata.AsObject;
  static serializeBinaryToWriter(message: FileMetadata, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): FileMetadata;
  static deserializeBinaryFromReader(message: FileMetadata, reader: jspb.BinaryReader): FileMetadata;
}

export namespace FileMetadata {
  export type AsObject = {
    type: string,
    sha1: string,
    size: number,
    name: string,
  }
}

