import {Component, OnInit} from '@angular/core';
import * as grpcWeb from "grpc-web";
import {Status} from "grpc-web";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {GitRepositoryServiceClient} from "../../grpc/git-repository_grpc_web_pb";
import {User} from "../../grpc/user_pb";
import {SessionService} from "../../session.service";
import {
  GetGitRepositoryOfUserReply,
  GetGitRepositoryOfUserRequest, GitRepository
} from "../../grpc/git-repository_pb";

@Component({
  selector: 'app-repositories',
  templateUrl: './repositories.component.html',
  styleUrls: ['./repositories.component.sass']
})
export class RepositoriesComponent implements OnInit {
  user: User = new User();
  repositories: GitRepository[] = [];

  constructor(
    private gitRepositoryServiceClient: GitRepositoryServiceClient,
    private sessionService: SessionService,
    private route: ActivatedRoute,
  ) {
    this.user.setUsername("User");
  }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
        const session = this.sessionService.getSession().subscribe(
          (session) => {
            // wait for session to be loaded from backend
            if (session == null) {
              return;
            }
            if (params.get("username") == session.getUserusername()) {
              this.user = session.getUser();
              // no need to load user data
              const request = new GetGitRepositoryOfUserRequest();
              request.setSessiontoken(session.getId());
              request.setUsername(session.getUserusername());
              const stream = this.gitRepositoryServiceClient.getOfUser(request, {}, () => {
              });

              stream.on("data", (response: GetGitRepositoryOfUserReply) => {
                this.repositories = response.getGitrepositoriesList();
              });
            }
          });
      }
    );
  }
}
