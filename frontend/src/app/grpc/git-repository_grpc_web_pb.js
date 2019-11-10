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
proto.helloworld = require('./git-repository_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.helloworld.GitRepositoryServiceClient =
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
proto.helloworld.GitRepositoryServicePromiseClient =
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
 *   !proto.helloworld.CreateGitRepositoryRequest,
 *   !proto.helloworld.CreateGitRepositoryReply>}
 */
const methodDescriptor_GitRepositoryService_Create = new grpc.web.MethodDescriptor(
  '/helloworld.GitRepositoryService/Create',
  grpc.web.MethodType.UNARY,
  proto.helloworld.CreateGitRepositoryRequest,
  proto.helloworld.CreateGitRepositoryReply,
  /** @param {!proto.helloworld.CreateGitRepositoryRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.CreateGitRepositoryReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.CreateGitRepositoryRequest,
 *   !proto.helloworld.CreateGitRepositoryReply>}
 */
const methodInfo_GitRepositoryService_Create = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.CreateGitRepositoryReply,
  /** @param {!proto.helloworld.CreateGitRepositoryRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.CreateGitRepositoryReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.CreateGitRepositoryRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.CreateGitRepositoryReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.CreateGitRepositoryReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.GitRepositoryServiceClient.prototype.create =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.GitRepositoryService/Create',
      request,
      metadata || {},
      methodDescriptor_GitRepositoryService_Create,
      callback);
};


/**
 * @param {!proto.helloworld.CreateGitRepositoryRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.CreateGitRepositoryReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.GitRepositoryServicePromiseClient.prototype.create =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.GitRepositoryService/Create',
      request,
      metadata || {},
      methodDescriptor_GitRepositoryService_Create);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.helloworld.GetGitRepositoryOfUserRequest,
 *   !proto.helloworld.GetGitRepositoryOfUserReply>}
 */
const methodDescriptor_GitRepositoryService_GetOfUser = new grpc.web.MethodDescriptor(
  '/helloworld.GitRepositoryService/GetOfUser',
  grpc.web.MethodType.UNARY,
  proto.helloworld.GetGitRepositoryOfUserRequest,
  proto.helloworld.GetGitRepositoryOfUserReply,
  /** @param {!proto.helloworld.GetGitRepositoryOfUserRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.GetGitRepositoryOfUserReply.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.helloworld.GetGitRepositoryOfUserRequest,
 *   !proto.helloworld.GetGitRepositoryOfUserReply>}
 */
const methodInfo_GitRepositoryService_GetOfUser = new grpc.web.AbstractClientBase.MethodInfo(
  proto.helloworld.GetGitRepositoryOfUserReply,
  /** @param {!proto.helloworld.GetGitRepositoryOfUserRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.helloworld.GetGitRepositoryOfUserReply.deserializeBinary
);


/**
 * @param {!proto.helloworld.GetGitRepositoryOfUserRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.helloworld.GetGitRepositoryOfUserReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.helloworld.GetGitRepositoryOfUserReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.helloworld.GitRepositoryServiceClient.prototype.getOfUser =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/helloworld.GitRepositoryService/GetOfUser',
      request,
      metadata || {},
      methodDescriptor_GitRepositoryService_GetOfUser,
      callback);
};


/**
 * @param {!proto.helloworld.GetGitRepositoryOfUserRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.helloworld.GetGitRepositoryOfUserReply>}
 *     A native promise that resolves to the response
 */
proto.helloworld.GitRepositoryServicePromiseClient.prototype.getOfUser =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/helloworld.GitRepositoryService/GetOfUser',
      request,
      metadata || {},
      methodDescriptor_GitRepositoryService_GetOfUser);
};


module.exports = proto.helloworld;

