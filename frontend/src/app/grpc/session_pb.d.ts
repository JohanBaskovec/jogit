import * as jspb from "google-protobuf"

import * as user_pb from './user_pb';

export class GetCurrentSessionRequest extends jspb.Message {
  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetCurrentSessionRequest.AsObject;
  static toObject(includeInstance: boolean, msg: GetCurrentSessionRequest): GetCurrentSessionRequest.AsObject;
  static serializeBinaryToWriter(message: GetCurrentSessionRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetCurrentSessionRequest;
  static deserializeBinaryFromReader(message: GetCurrentSessionRequest, reader: jspb.BinaryReader): GetCurrentSessionRequest;
}

export namespace GetCurrentSessionRequest {
  export type AsObject = {
    sessiontoken: string,
  }
}

export class GetCurrentSessionReply extends jspb.Message {
  getSession(): Session | undefined;
  setSession(value?: Session): void;
  hasSession(): boolean;
  clearSession(): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetCurrentSessionReply.AsObject;
  static toObject(includeInstance: boolean, msg: GetCurrentSessionReply): GetCurrentSessionReply.AsObject;
  static serializeBinaryToWriter(message: GetCurrentSessionReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetCurrentSessionReply;
  static deserializeBinaryFromReader(message: GetCurrentSessionReply, reader: jspb.BinaryReader): GetCurrentSessionReply;
}

export namespace GetCurrentSessionReply {
  export type AsObject = {
    session?: Session.AsObject,
  }
}

export class Session extends jspb.Message {
  getUserusername(): string;
  setUserusername(value: string): void;

  getId(): string;
  setId(value: string): void;

  getUser(): user_pb.User | undefined;
  setUser(value?: user_pb.User): void;
  hasUser(): boolean;
  clearUser(): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Session.AsObject;
  static toObject(includeInstance: boolean, msg: Session): Session.AsObject;
  static serializeBinaryToWriter(message: Session, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Session;
  static deserializeBinaryFromReader(message: Session, reader: jspb.BinaryReader): Session;
}

export namespace Session {
  export type AsObject = {
    userusername: string,
    id: string,
    user?: user_pb.User.AsObject,
  }
}

