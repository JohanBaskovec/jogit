package com.example.starter;

import com.example.starter.gprc.CreateGitRepositoryReply;
import com.example.starter.gprc.CreateGitRepositoryRequest;
import com.example.starter.gprc.GetGitRepositoryOfUserReply;
import com.example.starter.gprc.GetGitRepositoryOfUserRequest;
import com.example.starter.gprc.GitRepository;
import com.example.starter.gprc.GitRepositoryServiceGrpc;
import com.example.starter.gprc.Session;
import com.example.starter.validation.ObjectValidationResult;
import com.example.starter.validation.ObjectValidator;
import io.grpc.Status;
import io.vertx.core.Future;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;

import java.util.List;

class GitRepositoryEndpoint extends GitRepositoryServiceGrpc.GitRepositoryServiceVertxImplBase {
  private final PgPool pgClient;
  private final GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory;
  private final SessionRepositoryFactory sessionRepositoryFactory;
  private final ObjectValidator createGitRepositoryRequestValidator;
  private final ObjectValidator getGitRepositoryByUserRequestValidator;

  GitRepositoryEndpoint(
    PgPool pgClient,
    GitRepositoryRepositoryFactory gitRepositoryRepositoryFactory,
    SessionRepositoryFactory sessionRepositoryFactory,
    ObjectValidator createGitRepositoryRequestValidator,
    ObjectValidator getGitRepositoryByUserRequestValidator
  ) {
    super();
    this.pgClient = pgClient;
    this.gitRepositoryRepositoryFactory = gitRepositoryRepositoryFactory;
    this.sessionRepositoryFactory = sessionRepositoryFactory;
    this.createGitRepositoryRequestValidator = createGitRepositoryRequestValidator;
    this.getGitRepositoryByUserRequestValidator = getGitRepositoryByUserRequestValidator;
  }

  @Override
  public void create(CreateGitRepositoryRequest request, Future<CreateGitRepositoryReply> response) {
    RequestContext<CreateGitRepositoryRequest, CreateGitRepositoryReply> requestContext = new RequestContext<>(pgClient, request, response);
    requestContext.run(() -> {
      ObjectValidationResult objectValidationResult = createGitRepositoryRequestValidator.validate(request);
      // TODO: make a handle that automatically respond when result is invalid
      if (objectValidationResult.isInvalid()) {
        response.fail(Status.INVALID_ARGUMENT.asRuntimeException());
        return;
      }
      requestContext.begin((Transaction transaction) -> {
        SessionRepository sessionRepository = sessionRepositoryFactory.get(transaction);
        sessionRepository.getSessionById(request.getSessionToken(), new ErrorHandler<Session, CreateGitRepositoryReply>(requestContext) {
          @Override
          public void handleSuccess(Session session) {
            // TODO: make a handle that automatically respond when not authenticated
            if (session == null) {
              requestContext.handle(Future.failedFuture(Status.UNAUTHENTICATED.asException()));
              return;
            }
            GitRepositoryRepository gitRepositoryRepository = gitRepositoryRepositoryFactory.get(transaction);
            GitRepository gitRepository = GitRepository.newBuilder()
              .setUserUserName(session.getUserUsername())
              .setName(request.getName())
              .build();
            gitRepositoryRepository.insert(gitRepository, new ErrorHandler<Void, CreateGitRepositoryReply>(handler) {
              @Override
              public void handleSuccess(Void result) {
                CreateGitRepositoryReply reply = CreateGitRepositoryReply.newBuilder().build();
                handler.handle(Future.succeededFuture(reply));
              }
            });
          }
        });
      });
    });
  }

  @Override
  public void getOfUser(GetGitRepositoryOfUserRequest request, Future<GetGitRepositoryOfUserReply> response) {
    RequestContext<GetGitRepositoryOfUserRequest, GetGitRepositoryOfUserReply> requestContext = new RequestContext<>(pgClient, request, response);
    requestContext.run(() -> {
      ObjectValidationResult objectValidationResult = getGitRepositoryByUserRequestValidator.validate(request);
      // TODO: make a handle that automatically respond when result is invalid
      if (objectValidationResult.isInvalid()) {
        response.fail(Status.INVALID_ARGUMENT.asRuntimeException());
        return;
      }
      requestContext.begin((Transaction transaction) -> {
        GitRepositoryRepository gitRepositoryRepository = gitRepositoryRepositoryFactory.get(transaction);
        gitRepositoryRepository.getByUsername(request.getUsername(), new ErrorHandler<List<GitRepository>, GetGitRepositoryOfUserReply>(requestContext) {
          @Override
          public void handleSuccess(List<GitRepository> gitRepositories) {
            GetGitRepositoryOfUserReply reply = GetGitRepositoryOfUserReply.newBuilder()
              .addAllGitRepositories(gitRepositories)
              .build();
            handler.handle(Future.succeededFuture(reply));
          }
        });
      });
    });
  }

}
