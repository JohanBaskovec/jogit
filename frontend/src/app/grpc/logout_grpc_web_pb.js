/**
 * @fileoverview gRPC-Web generated client stub for helloworld
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.helloworld = require('./logout_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.LogoutClient =
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
proto.helloworld.LogoutPromiseClient =
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
 *   !proto.helloworld.LogoutRequest,
 *   !proto.helloworld.LogoutReply>}
 */
const methodDescriptor_Logout_Logout = new grpc.web.MethodDescriptor(
  '/helloworld.Logout/Logout',
  grpc.web.MethodType.UNARY,
  proto.helloworld.LogoutRequest,
  proto.helloworld.LogoutReply,
  /**
   * @param {!proto.helloworld.LogoutRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.LogoutReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.LogoutRequest,
 *   !proto.helloworld.LogoutReply>}
 */
const methodInfo_Logout_Logout = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.LogoutReply,
  /**
   * @param {!proto.helloworld.LogoutRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.LogoutReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.LogoutRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.LogoutReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.LogoutReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.LogoutClient.prototype.logout =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.Logout/Logout',
      request,
      metadata || {},
      methodDescriptor_Logout_Logout,
      callback);
};


/**
 * @param {!proto.helloworld.LogoutRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.LogoutReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.LogoutPromiseClient.prototype.logout =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.Logout/Logout',
      request,
      metadata || {},
      methodDescriptor_Logout_Logout);
};


module.exports = proto.helloworld;

