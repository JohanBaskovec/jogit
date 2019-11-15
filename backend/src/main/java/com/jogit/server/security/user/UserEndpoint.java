package com.jogit.server.security.user;

import com.jogit.server.git.repository.GitRepositoryRepositoryFactory;
import com.jogit.server.grpc.*;
import com.jogit.server.grpc.Session;
import com.jogit.server.grpc.User;
import com.jogit.server.security.session.SessionRepositoryFactory;
import com.jogit.server.validation.ObjectValidator;
import com.jogit.server.vertx.ErrorHandler;
import com.jogit.server.vertx.RequestContext;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;

import java.util.List;

public class UserEndpoint extends UserServiceGrpc.UserServiceVertxImplBase {
  private final PgPool pgClient;
  private final GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory;
  private final SessionRepositoryFactory sessionRepositoryFactory;
  private final ObjectValidator<UpdateSshPublicKeyRequest> updateSshPublicKeyRequestValidator;
  private final ObjectValidator<DeleteSshPublicKeyRequest> deleteSshPublicKeyRequestValidator;
  private final ObjectValidator<AddSshPublicKeyRequest> addSshPublicKeyRequestValidator;
  private ObjectValidator<GetSshPublicKeyRequest> getSshPublicKeyRequestValidator;
  private final UserRepositoryFactory userRepositoryFactory;
  private final UserService userService;

  public UserEndpoint(
    PgPool pgClient,
    GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory,
    SessionRepositoryFactory sessionRepositoryFactory,
    ObjectValidator<UpdateSshPublicKeyRequest> updateSshPublicKeyRequestValidator,
    ObjectValidator<DeleteSshPublicKeyRequest> deleteSshPublicKeyRequestValidator,
    ObjectValidator<AddSshPublicKeyRequest> addSshPublicKeyRequestValidator,
    ObjectValidator<GetSshPublicKeyRequest> getSshPublicKeyRequestValidator,
    UserRepositoryFactory userRepositoryFactory,
    UserService userService
  ) {
    super();
    this.pgClient = pgClient;
    this.gitRepositoryRepositoryFactory = gitRepositoryRepositoryFactory;
    this.sessionRepositoryFactory = sessionRepositoryFactory;
    this.updateSshPublicKeyRequestValidator = updateSshPublicKeyRequestValidator;
    this.deleteSshPublicKeyRequestValidator = deleteSshPublicKeyRequestValidator;
    this.addSshPublicKeyRequestValidator = addSshPublicKeyRequestValidator;
    this.getSshPublicKeyRequestValidator = getSshPublicKeyRequestValidator;
    this.userRepositoryFactory = userRepositoryFactory;
    this.userService = userService;
  }

  @Override
  public void getSshPublicKeys(GetSshPublicKeyRequest request, Future<GetSshPublicKeyReply> response) {
    RequestContext<GetSshPublicKeyRequest, GetSshPublicKeyReply> requestContext = new RequestContext<>(pgClient, request, response);
    requestContext.validateRequestAndAuthenticate(
      this.getSshPublicKeyRequestValidator,
      sessionRepositoryFactory,
      request.getSessionToken(),
      new ErrorHandler<Session, GetSshPublicKeyReply>(requestContext) {
        @Override
        public void handleSuccess(Session session) {
          if (!session.getUser().getUsername().equals(request.getUsername())) {
            requestContext.respondWithUnauthenticatedError();
            return;
          }
          UserRepository userRepository = userRepositoryFactory.get(requestContext.getTransaction());
          userRepository.getSshKeys(
            new UserRepository.SshKeySelectWhere()
              .setDeleted(false)
              .setUsername(request.getUsername()),
            new ErrorHandler<List<SshPublicKey>, GetSshPublicKeyReply>(requestContext) {
              @Override
              public void handleSuccess(List<SshPublicKey> keys) {
                requestContext.handle(Future.succeededFuture(GetSshPublicKeyReply.newBuilder()
                  .addAllSshPublicKeys(keys)
                  .build()));
              }
            });
        }
      }
    );
  }

  @Override
  public void addPublicSshKey(AddSshPublicKeyRequest request, Future<AddSshPublicKeyReply> response) {
    RequestContext<AddSshPublicKeyRequest, AddSshPublicKeyReply> requestContext = new RequestContext<>(pgClient, request, response);
    requestContext.validateRequestAndAuthenticate(
      this.addSshPublicKeyRequestValidator,
      sessionRepositoryFactory,
      request.getSessionToken(),
      new ErrorHandler<Session, AddSshPublicKeyReply>(requestContext) {
        @Override
        public void handleSuccess(Session session) {
          if (!session.getUser().getUsername().equals(request.getUsername())) {
            requestContext.respondWithUnauthenticatedError();
            return;
          }
          User user = session.getUser();
          UserRepository userRepository = userRepositoryFactory.get(requestContext.getTransaction());
          SshPublicKey key = SshPublicKey.newBuilder()
            .setKey(request.getSshPublicKey())
            .setUserUsername(request.getUsername())
            .setDeleted(false)
            .build();
          userRepository.addSshKeys(user, key, new ErrorHandler<Void, AddSshPublicKeyReply>(requestContext) {
            @Override
            public void handleSuccess(Void result) {
              requestContext.handle(Future.succeededFuture(AddSshPublicKeyReply.newBuilder().build()));
            }
          });
        }
      }
    );
  }

  @Override
  public void deleteSshPublicKey(DeleteSshPublicKeyRequest request, Future<DeleteSshPublicKeyReply> response) {
    RequestContext<DeleteSshPublicKeyRequest, DeleteSshPublicKeyReply> requestContext = new RequestContext<>(pgClient, request, response);
    requestContext.validateRequestAndAuthenticate(
      this.deleteSshPublicKeyRequestValidator,
      sessionRepositoryFactory,
      request.getSessionToken(),
      new ErrorHandler<Session, DeleteSshPublicKeyReply>(requestContext) {
        @Override
        public void handleSuccess(Session session) {
          UserRepository userRepository = userRepositoryFactory.get(requestContext.getTransaction());

          userRepository.getSshKeys(
            new UserRepository.SshKeySelectWhere()
              .setDeleted(false)
              .setId(request.getSshPublicKeyId()),
            new ErrorHandler<List<SshPublicKey>, DeleteSshPublicKeyReply>(requestContext) {
              @Override
              public void handleSuccess(List<SshPublicKey> sshKeys) {
                if (sshKeys.size() == 0) {
                  requestContext.respondWithInvalidArgumentsError();
                  return;
                }
                SshPublicKey sshPublicKey = sshKeys.get(0);
                if (!session.getUser().getUsername().equals(sshPublicKey.getUserUsername())) {
                  requestContext.respondWithUnauthenticatedError();
                  return;
                }
                userRepository.deleteSshKey(sshPublicKey.getId(), new ErrorHandler<Void, DeleteSshPublicKeyReply>(requestContext) {
                  @Override
                  public void handleSuccess(Void result) {
                    requestContext.handle(Future.succeededFuture(DeleteSshPublicKeyReply.newBuilder().build()));
                  }
                });

              }
            }
          );
        }
      }
    );
  }

  @Override
  public void updateSshPublicKey(UpdateSshPublicKeyRequest request, Future<UpdateSshPublicKeyReply> response) {
    RequestContext<UpdateSshPublicKeyRequest, UpdateSshPublicKeyReply> requestContext = new RequestContext<>(pgClient, request, response);
    requestContext.validateRequestAndAuthenticate(
      this.updateSshPublicKeyRequestValidator,
      sessionRepositoryFactory,
      request.getSessionToken(),
      new ErrorHandler<Session, UpdateSshPublicKeyReply>(requestContext) {
        @Override
        public void handleSuccess(Session session) {
          UserRepository userRepository = userRepositoryFactory.get(requestContext.getTransaction());

          userRepository.getSshKeys(
            new UserRepository.SshKeySelectWhere()
              .setDeleted(false)
              .setId(request.getSshPublicKey().getId()),
            new ErrorHandler<List<SshPublicKey>, UpdateSshPublicKeyReply>(requestContext) {
              @Override
              public void handleSuccess(List<SshPublicKey> sshKeys) {
                if (sshKeys.size() == 0) {
                  requestContext.respondWithInvalidArgumentsError();
                  return;
                }
                SshPublicKey sshPublicKey = sshKeys.get(0);
                if (!session.getUser().getUsername().equals(sshPublicKey.getUserUsername())) {
                  requestContext.respondWithUnauthenticatedError();
                  return;
                }
                userRepository.updateSshKey(sshPublicKey, new ErrorHandler<Void, UpdateSshPublicKeyReply>(requestContext) {
                  @Override
                  public void handleSuccess(Void result) {
                    requestContext.handle(Future.succeededFuture(UpdateSshPublicKeyReply.newBuilder().build()));
                  }
                });

              }
            }
          );
        }
      }
    );
  }
}
