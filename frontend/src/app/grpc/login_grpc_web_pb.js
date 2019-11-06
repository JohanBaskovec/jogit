/**
 * @fileoverview gRPC-Web generated client stub for helloworld
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');


var session_pb = require('./session_pb.js')
const proto = {};
proto.helloworld = require('./login_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.LoginClient =
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

  /**
   * @private @const {?Object} The credentials to be used to connect
   *    to the server
   */
  this.credentials_ = credentials;

  /**
   * @private @const {?Object} Options for the client
   */
  this.options_ = options;
};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.LoginPromiseClient =
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

  /**
   * @private @const {?Object} The credentials to be used to connect
   *    to the server
   */
  this.credentials_ = credentials;

  /**
   * @private @const {?Object} Options for the client
   */
  this.options_ = options;
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.helloworld.LoginRequest,
 *   !proto.helloworld.LoginReply>}
 */
const methodDescriptor_Login_Login = new grpc.web.MethodDescriptor(
  '/helloworld.Login/Login',
  grpc.web.MethodType.UNARY,
  proto.helloworld.LoginRequest,
  proto.helloworld.LoginReply,
  /** @param {!proto.helloworld.LoginRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.LoginReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.LoginRequest,
 *   !proto.helloworld.LoginReply>}
 */
const methodInfo_Login_Login = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.LoginReply,
  /** @param {!proto.helloworld.LoginRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.LoginReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.LoginRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.LoginReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.LoginReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.LoginClient.prototype.login =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.Login/Login',
      request,
      metadata || {},
      methodDescriptor_Login_Login,
      callback);
};


/**
 * @param {!proto.helloworld.LoginRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.LoginReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.LoginPromiseClient.prototype.login =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.Login/Login',
      request,
      metadata || {},
      methodDescriptor_Login_Login);
};


module.exports = proto.helloworld;

