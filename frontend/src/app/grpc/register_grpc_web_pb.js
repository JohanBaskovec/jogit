/**
 * @fileoverview gRPC-Web generated client stub for helloworld
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.helloworld = require('./register_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.RegisterClient =
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
proto.helloworld.RegisterPromiseClient =
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
 *   !proto.helloworld.RegisterRequest,
 *   !proto.helloworld.RegisterReply>}
 */
const methodDescriptor_Register_Register = new grpc.web.MethodDescriptor(
  '/helloworld.Register/Register',
  grpc.web.MethodType.UNARY,
  proto.helloworld.RegisterRequest,
  proto.helloworld.RegisterReply,
  /** @param {!proto.helloworld.RegisterRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.RegisterReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.RegisterRequest,
 *   !proto.helloworld.RegisterReply>}
 */
const methodInfo_Register_Register = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.RegisterReply,
  /** @param {!proto.helloworld.RegisterRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.RegisterReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.RegisterRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.RegisterReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.RegisterReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.RegisterClient.prototype.register =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.Register/Register',
      request,
      metadata || {},
      methodDescriptor_Register_Register,
      callback);
};


/**
 * @param {!proto.helloworld.RegisterRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.RegisterReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.RegisterPromiseClient.prototype.register =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.Register/Register',
      request,
      metadata || {},
      methodDescriptor_Register_Register);
};


module.exports = proto.helloworld;

