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

