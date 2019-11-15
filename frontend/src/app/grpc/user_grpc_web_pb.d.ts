import * as grpcWeb from 'grpc-web';

import {
  AddSshPublicKeyReply,
  AddSshPublicKeyRequest,
  DeleteSshPublicKeyReply,
  DeleteSshPublicKeyRequest,
  GetSshPublicKeyReply,
  GetSshPublicKeyRequest,
  UpdateSshPublicKeyReply,
  UpdateSshPublicKeyRequest} from './user_pb';

export class UserServiceClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  getSshPublicKeys(
    request: GetSshPublicKeyRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: GetSshPublicKeyReply) => void
  ): grpcWeb.ClientReadableStream<GetSshPublicKeyReply>;

  addPublicSshKey(
    request: AddSshPublicKeyRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: AddSshPublicKeyReply) => void
  ): grpcWeb.ClientReadableStream<AddSshPublicKeyReply>;

  deleteSshPublicKey(
    request: DeleteSshPublicKeyRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: DeleteSshPublicKeyReply) => void
  ): grpcWeb.ClientReadableStream<DeleteSshPublicKeyReply>;

  updateSshPublicKey(
    request: UpdateSshPublicKeyRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: UpdateSshPublicKeyReply) => void
  ): grpcWeb.ClientReadableStream<UpdateSshPublicKeyReply>;

}

export class UserServicePromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  getSshPublicKeys(
    request: GetSshPublicKeyRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<GetSshPublicKeyReply>;

  addPublicSshKey(
    request: AddSshPublicKeyRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<AddSshPublicKeyReply>;

  deleteSshPublicKey(
    request: DeleteSshPublicKeyRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<DeleteSshPublicKeyReply>;

  updateSshPublicKey(
    request: UpdateSshPublicKeyRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<UpdateSshPublicKeyReply>;

}

