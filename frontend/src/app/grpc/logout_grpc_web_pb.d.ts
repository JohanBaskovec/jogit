import * as grpcWeb from 'grpc-web';

import {
  LogoutReply,
  LogoutRequest} from './logout_pb';

export class LogoutClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  logout(
    request: LogoutRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: LogoutReply) => void
  ): grpcWeb.ClientReadableStream<LogoutReply>;

}

export class LogoutPromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  logout(
    request: LogoutRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<LogoutReply>;

}

