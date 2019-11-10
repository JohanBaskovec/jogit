import * as grpcWeb from 'grpc-web';

import * as user_pb from './user_pb';

import {
  CreateGitRepositoryReply,
  CreateGitRepositoryRequest,
  GetGitRepositoryDirectoryReply,
  GetGitRepositoryDirectoryRequest,
  GetGitRepositoryOfUserReply,
  GetGitRepositoryOfUserRequest} from './git-repository_pb';

export class GitRepositoryServiceClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  create(
    request: CreateGitRepositoryRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: CreateGitRepositoryReply) => void
  ): grpcWeb.ClientReadableStream<CreateGitRepositoryReply>;

  getOfUser(
    request: GetGitRepositoryOfUserRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: GetGitRepositoryOfUserReply) => void
  ): grpcWeb.ClientReadableStream<GetGitRepositoryOfUserReply>;

  getDirectory(
    request: GetGitRepositoryDirectoryRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: GetGitRepositoryDirectoryReply) => void
  ): grpcWeb.ClientReadableStream<GetGitRepositoryDirectoryReply>;

}

export class GitRepositoryServicePromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  create(
    request: CreateGitRepositoryRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<CreateGitRepositoryReply>;

  getOfUser(
    request: GetGitRepositoryOfUserRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<GetGitRepositoryOfUserReply>;

  getDirectory(
    request: GetGitRepositoryDirectoryRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<GetGitRepositoryDirectoryReply>;

}

