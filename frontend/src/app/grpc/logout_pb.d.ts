import * as jspb from "google-protobuf"

export class LogoutRequest extends jspb.Message {
  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): LogoutRequest.AsObject;
  static toObject(includeInstance: boolean, msg: LogoutRequest): LogoutRequest.AsObject;
  static serializeBinaryToWriter(message: LogoutRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): LogoutRequest;
  static deserializeBinaryFromReader(message: LogoutRequest, reader: jspb.BinaryReader): LogoutRequest;
}

export namespace LogoutRequest {
  export type AsObject = {
    sessiontoken: string,
  }
}

export class LogoutReply extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): LogoutReply.AsObject;
  static toObject(includeInstance: boolean, msg: LogoutReply): LogoutReply.AsObject;
  static serializeBinaryToWriter(message: LogoutReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): LogoutReply;
  static deserializeBinaryFromReader(message: LogoutReply, reader: jspb.BinaryReader): LogoutReply;
}

export namespace LogoutReply {
  export type AsObject = {
  }
}

