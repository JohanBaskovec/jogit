/**
 * @fileoverview gRPC-Web generated client stub for helloworld
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');


var user_pb = require('./user_pb.js')
const proto = {};
proto.helloworld = require('./session_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.SessionServiceClient =
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
proto.helloworld.SessionServicePromiseClient =
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
 *   !proto.helloworld.GetCurrentSessionRequest,
 *   !proto.helloworld.GetCurrentSessionReply>}
 */
const methodDescriptor_SessionService_getCurrentSession = new grpc.web.MethodDescriptor(
  '/helloworld.SessionService/getCurrentSession',
  grpc.web.MethodType.UNARY,
  proto.helloworld.GetCurrentSessionRequest,
  proto.helloworld.GetCurrentSessionReply,
  /** @param {!proto.helloworld.GetCurrentSessionRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.GetCurrentSessionReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.GetCurrentSessionRequest,
 *   !proto.helloworld.GetCurrentSessionReply>}
 */
const methodInfo_SessionService_getCurrentSession = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.GetCurrentSessionReply,
  /** @param {!proto.helloworld.GetCurrentSessionRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.GetCurrentSessionReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.GetCurrentSessionRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.GetCurrentSessionReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.GetCurrentSessionReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.SessionServiceClient.prototype.getCurrentSession =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.SessionService/getCurrentSession',
      request,
      metadata || {},
      methodDescriptor_SessionService_getCurrentSession,
      callback);
};


/**
 * @param {!proto.helloworld.GetCurrentSessionRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.GetCurrentSessionReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.SessionServicePromiseClient.prototype.getCurrentSession =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.SessionService/getCurrentSession',
      request,
      metadata || {},
      methodDescriptor_SessionService_getCurrentSession);
};


module.exports = proto.helloworld;

