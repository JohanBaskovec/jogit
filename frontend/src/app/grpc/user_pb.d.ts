import * as jspb from "google-protobuf"

export class User extends jspb.Message {
  getUsername(): string;
  setUsername(value: string): void;

  getPassword(): string;
  setPassword(value: string): void;

  getPasswordsalt(): string;
  setPasswordsalt(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): User.AsObject;
  static toObject(includeInstance: boolean, msg: User): User.AsObject;
  static serializeBinaryToWriter(message: User, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): User;
  static deserializeBinaryFromReader(message: User, reader: jspb.BinaryReader): User;
}

export namespace User {
  export type AsObject = {
    username: string,
    password: string,
    passwordsalt: string,
  }
}

export class GetSshPublicKeyRequest extends jspb.Message {
  getUsername(): string;
  setUsername(value: string): void;

  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetSshPublicKeyRequest.AsObject;
  static toObject(includeInstance: boolean, msg: GetSshPublicKeyRequest): GetSshPublicKeyRequest.AsObject;
  static serializeBinaryToWriter(message: GetSshPublicKeyRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetSshPublicKeyRequest;
  static deserializeBinaryFromReader(message: GetSshPublicKeyRequest, reader: jspb.BinaryReader): GetSshPublicKeyRequest;
}

export namespace GetSshPublicKeyRequest {
  export type AsObject = {
    username: string,
    sessiontoken: string,
  }
}

export class GetSshPublicKeyReply extends jspb.Message {
  getSshpublickeysList(): Array<SshPublicKey>;
  setSshpublickeysList(value: Array<SshPublicKey>): void;
  clearSshpublickeysList(): void;
  addSshpublickeys(value?: SshPublicKey, index?: number): SshPublicKey;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): GetSshPublicKeyReply.AsObject;
  static toObject(includeInstance: boolean, msg: GetSshPublicKeyReply): GetSshPublicKeyReply.AsObject;
  static serializeBinaryToWriter(message: GetSshPublicKeyReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): GetSshPublicKeyReply;
  static deserializeBinaryFromReader(message: GetSshPublicKeyReply, reader: jspb.BinaryReader): GetSshPublicKeyReply;
}

export namespace GetSshPublicKeyReply {
  export type AsObject = {
    sshpublickeysList: Array<SshPublicKey.AsObject>,
  }
}

export class AddSshPublicKeyRequest extends jspb.Message {
  getUsername(): string;
  setUsername(value: string): void;

  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  getSshpublickey(): string;
  setSshpublickey(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): AddSshPublicKeyRequest.AsObject;
  static toObject(includeInstance: boolean, msg: AddSshPublicKeyRequest): AddSshPublicKeyRequest.AsObject;
  static serializeBinaryToWriter(message: AddSshPublicKeyRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): AddSshPublicKeyRequest;
  static deserializeBinaryFromReader(message: AddSshPublicKeyRequest, reader: jspb.BinaryReader): AddSshPublicKeyRequest;
}

export namespace AddSshPublicKeyRequest {
  export type AsObject = {
    username: string,
    sessiontoken: string,
    sshpublickey: string,
  }
}

export class AddSshPublicKeyReply extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): AddSshPublicKeyReply.AsObject;
  static toObject(includeInstance: boolean, msg: AddSshPublicKeyReply): AddSshPublicKeyReply.AsObject;
  static serializeBinaryToWriter(message: AddSshPublicKeyReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): AddSshPublicKeyReply;
  static deserializeBinaryFromReader(message: AddSshPublicKeyReply, reader: jspb.BinaryReader): AddSshPublicKeyReply;
}

export namespace AddSshPublicKeyReply {
  export type AsObject = {
  }
}

export class DeleteSshPublicKeyRequest extends jspb.Message {
  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  getSshpublickeyid(): number;
  setSshpublickeyid(value: number): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): DeleteSshPublicKeyRequest.AsObject;
  static toObject(includeInstance: boolean, msg: DeleteSshPublicKeyRequest): DeleteSshPublicKeyRequest.AsObject;
  static serializeBinaryToWriter(message: DeleteSshPublicKeyRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): DeleteSshPublicKeyRequest;
  static deserializeBinaryFromReader(message: DeleteSshPublicKeyRequest, reader: jspb.BinaryReader): DeleteSshPublicKeyRequest;
}

export namespace DeleteSshPublicKeyRequest {
  export type AsObject = {
    sessiontoken: string,
    sshpublickeyid: number,
  }
}

export class DeleteSshPublicKeyReply extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): DeleteSshPublicKeyReply.AsObject;
  static toObject(includeInstance: boolean, msg: DeleteSshPublicKeyReply): DeleteSshPublicKeyReply.AsObject;
  static serializeBinaryToWriter(message: DeleteSshPublicKeyReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): DeleteSshPublicKeyReply;
  static deserializeBinaryFromReader(message: DeleteSshPublicKeyReply, reader: jspb.BinaryReader): DeleteSshPublicKeyReply;
}

export namespace DeleteSshPublicKeyReply {
  export type AsObject = {
  }
}

export class UpdateSshPublicKeyRequest extends jspb.Message {
  getSessiontoken(): string;
  setSessiontoken(value: string): void;

  getSshpublickey(): SshPublicKey | undefined;
  setSshpublickey(value?: SshPublicKey): void;
  hasSshpublickey(): boolean;
  clearSshpublickey(): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): UpdateSshPublicKeyRequest.AsObject;
  static toObject(includeInstance: boolean, msg: UpdateSshPublicKeyRequest): UpdateSshPublicKeyRequest.AsObject;
  static serializeBinaryToWriter(message: UpdateSshPublicKeyRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): UpdateSshPublicKeyRequest;
  static deserializeBinaryFromReader(message: UpdateSshPublicKeyRequest, reader: jspb.BinaryReader): UpdateSshPublicKeyRequest;
}

export namespace UpdateSshPublicKeyRequest {
  export type AsObject = {
    sessiontoken: string,
    sshpublickey?: SshPublicKey.AsObject,
  }
}

export class UpdateSshPublicKeyReply extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): UpdateSshPublicKeyReply.AsObject;
  static toObject(includeInstance: boolean, msg: UpdateSshPublicKeyReply): UpdateSshPublicKeyReply.AsObject;
  static serializeBinaryToWriter(message: UpdateSshPublicKeyReply, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): UpdateSshPublicKeyReply;
  static deserializeBinaryFromReader(message: UpdateSshPublicKeyReply, reader: jspb.BinaryReader): UpdateSshPublicKeyReply;
}

export namespace UpdateSshPublicKeyReply {
  export type AsObject = {
  }
}

export class SshPublicKey extends jspb.Message {
  getId(): number;
  setId(value: number): void;

  getKey(): string;
  setKey(value: string): void;

  getUserusername(): string;
  setUserusername(value: string): void;

  getDeleted(): boolean;
  setDeleted(value: boolean): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): SshPublicKey.AsObject;
  static toObject(includeInstance: boolean, msg: SshPublicKey): SshPublicKey.AsObject;
  static serializeBinaryToWriter(message: SshPublicKey, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): SshPublicKey;
  static deserializeBinaryFromReader(message: SshPublicKey, reader: jspb.BinaryReader): SshPublicKey;
}

export namespace SshPublicKey {
  export type AsObject = {
    id: number,
    key: string,
    userusername: string,
    deleted: boolean,
  }
}

