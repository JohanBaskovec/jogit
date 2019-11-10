import * as grpcWeb from 'grpc-web';

import * as user_pb from './user_pb';

import {
  GetCurrentSessionReply,
  GetCurrentSessionRequest} from './session_pb';

export class SessionServiceClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  getCurrentSession(
    request: GetCurrentSessionRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: GetCurrentSessionReply) => void
  ): grpcWeb.ClientReadableStream<GetCurrentSessionReply>;

}

export class SessionServicePromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  getCurrentSession(
    request: GetCurrentSessionRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<GetCurrentSessionReply>;

}

