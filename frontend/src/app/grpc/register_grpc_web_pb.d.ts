import * as grpcWeb from 'grpc-web';

import {
  RegisterReply,
  RegisterRequest} from './register_pb';

export class RegisterClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  register(
    request: RegisterRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: RegisterReply) => void
  ): grpcWeb.ClientReadableStream<RegisterReply>;

}

export class RegisterPromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  register(
    request: RegisterRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<RegisterReply>;

}

