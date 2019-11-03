package com.example.starter;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class CreateRepositoryHandler implements Handler<RoutingContext> {
  private final RandomService randomService;
  private final GitRepositoryService gitRepositoryService;

  public CreateRepositoryHandler(RandomService randomService, GitRepositoryService gitRepositoryService) {
    this.randomService = randomService;
    this.gitRepositoryService = gitRepositoryService;
  }

  @Override
  public void handle(RoutingContext rc) {
    String repositoryUser = rc.user().principal().getString("username");
    String repositoryName = randomService.getRandomString(20);
    // TODO: make async
    gitRepositoryService.createRepository(repositoryUser, repositoryName);
    rc.response().setStatusCode(202).end();
  }
}
