/**
 * @fileoverview gRPC-Web generated client stub for helloworld
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.helloworld = require('./user_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.UserServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.UserServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.helloworld.GetSshPublicKeyRequest,
 *   !proto.helloworld.GetSshPublicKeyReply>}
 */
const methodDescriptor_UserService_GetSshPublicKeys = new grpc.web.MethodDescriptor(
  '/helloworld.UserService/GetSshPublicKeys',
  grpc.web.MethodType.UNARY,
  proto.helloworld.GetSshPublicKeyRequest,
  proto.helloworld.GetSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.GetSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.GetSshPublicKeyReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.GetSshPublicKeyRequest,
 *   !proto.helloworld.GetSshPublicKeyReply>}
 */
const methodInfo_UserService_GetSshPublicKeys = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.GetSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.GetSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.GetSshPublicKeyReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.GetSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.GetSshPublicKeyReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.GetSshPublicKeyReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.UserServiceClient.prototype.getSshPublicKeys =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.UserService/GetSshPublicKeys',
      request,
      metadata || {},
      methodDescriptor_UserService_GetSshPublicKeys,
      callback);
};


/**
 * @param {!proto.helloworld.GetSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.GetSshPublicKeyReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.UserServicePromiseClient.prototype.getSshPublicKeys =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.UserService/GetSshPublicKeys',
      request,
      metadata || {},
      methodDescriptor_UserService_GetSshPublicKeys);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.helloworld.AddSshPublicKeyRequest,
 *   !proto.helloworld.AddSshPublicKeyReply>}
 */
const methodDescriptor_UserService_AddPublicSshKey = new grpc.web.MethodDescriptor(
  '/helloworld.UserService/AddPublicSshKey',
  grpc.web.MethodType.UNARY,
  proto.helloworld.AddSshPublicKeyRequest,
  proto.helloworld.AddSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.AddSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.AddSshPublicKeyReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.AddSshPublicKeyRequest,
 *   !proto.helloworld.AddSshPublicKeyReply>}
 */
const methodInfo_UserService_AddPublicSshKey = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.AddSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.AddSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.AddSshPublicKeyReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.AddSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.AddSshPublicKeyReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.AddSshPublicKeyReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.UserServiceClient.prototype.addPublicSshKey =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.UserService/AddPublicSshKey',
      request,
      metadata || {},
      methodDescriptor_UserService_AddPublicSshKey,
      callback);
};


/**
 * @param {!proto.helloworld.AddSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.AddSshPublicKeyReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.UserServicePromiseClient.prototype.addPublicSshKey =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.UserService/AddPublicSshKey',
      request,
      metadata || {},
      methodDescriptor_UserService_AddPublicSshKey);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.helloworld.DeleteSshPublicKeyRequest,
 *   !proto.helloworld.DeleteSshPublicKeyReply>}
 */
const methodDescriptor_UserService_DeleteSshPublicKey = new grpc.web.MethodDescriptor(
  '/helloworld.UserService/DeleteSshPublicKey',
  grpc.web.MethodType.UNARY,
  proto.helloworld.DeleteSshPublicKeyRequest,
  proto.helloworld.DeleteSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.DeleteSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.DeleteSshPublicKeyReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.DeleteSshPublicKeyRequest,
 *   !proto.helloworld.DeleteSshPublicKeyReply>}
 */
const methodInfo_UserService_DeleteSshPublicKey = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.DeleteSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.DeleteSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.DeleteSshPublicKeyReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.DeleteSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.DeleteSshPublicKeyReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.DeleteSshPublicKeyReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.UserServiceClient.prototype.deleteSshPublicKey =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.UserService/DeleteSshPublicKey',
      request,
      metadata || {},
      methodDescriptor_UserService_DeleteSshPublicKey,
      callback);
};


/**
 * @param {!proto.helloworld.DeleteSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.DeleteSshPublicKeyReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.UserServicePromiseClient.prototype.deleteSshPublicKey =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.UserService/DeleteSshPublicKey',
      request,
      metadata || {},
      methodDescriptor_UserService_DeleteSshPublicKey);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.helloworld.UpdateSshPublicKeyRequest,
 *   !proto.helloworld.UpdateSshPublicKeyReply>}
 */
const methodDescriptor_UserService_UpdateSshPublicKey = new grpc.web.MethodDescriptor(
  '/helloworld.UserService/UpdateSshPublicKey',
  grpc.web.MethodType.UNARY,
  proto.helloworld.UpdateSshPublicKeyRequest,
  proto.helloworld.UpdateSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.UpdateSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.UpdateSshPublicKeyReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.UpdateSshPublicKeyRequest,
 *   !proto.helloworld.UpdateSshPublicKeyReply>}
 */
const methodInfo_UserService_UpdateSshPublicKey = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.UpdateSshPublicKeyReply,
  /**
   * @param {!proto.helloworld.UpdateSshPublicKeyRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.UpdateSshPublicKeyReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.UpdateSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.UpdateSshPublicKeyReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.UpdateSshPublicKeyReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.UserServiceClient.prototype.updateSshPublicKey =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.UserService/UpdateSshPublicKey',
      request,
      metadata || {},
      methodDescriptor_UserService_UpdateSshPublicKey,
      callback);
};


/**
 * @param {!proto.helloworld.UpdateSshPublicKeyRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.UpdateSshPublicKeyReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.UserServicePromiseClient.prototype.updateSshPublicKey =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.UserService/UpdateSshPublicKey',
      request,
      metadata || {},
      methodDescriptor_UserService_UpdateSshPublicKey);
};


module.exports = proto.helloworld;

