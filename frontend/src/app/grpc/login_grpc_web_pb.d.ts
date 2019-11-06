import * as grpcWeb from 'grpc-web';

import * as session_pb from './session_pb';

import {
  LoginReply,
  LoginRequest} from './login_pb';

export class LoginClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  login(
    request: LoginRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: LoginReply) => void
  ): grpcWeb.ClientReadableStream<LoginReply>;

}

export class LoginPromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  login(
    request: LoginRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<LoginReply>;

}

