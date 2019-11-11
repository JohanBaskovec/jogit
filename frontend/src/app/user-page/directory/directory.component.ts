import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {GitRepositoryServiceClient} from '../../grpc/git-repository_grpc_web_pb';
import {FileMetadata, GetGitRepositoryDirectoryRequest} from '../../grpc/git-repository_pb';
import {SessionService} from '../../session.service';

@Component({
  selector: 'app-directory',
  templateUrl: './directory.component.html',
  styleUrls: ['./directory.component.sass']
})
export class DirectoryComponent implements OnInit {
  files: FileMetadata[];

  constructor(
    private route: ActivatedRoute,
    private gitClient: GitRepositoryServiceClient,
    private sessionService: SessionService,
  ) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      console.log(params);
      const repositoryName = params.get('repositoryName');
      const directoryPath = params.get('directoryPath');
      const username = this.route.parent.snapshot.paramMap.get('username');
      const request = new GetGitRepositoryDirectoryRequest();
      request.setDirectorypath(directoryPath);
      request.setUsername(username);
      request.setRepository(repositoryName);
      request.setSessiontoken(this.sessionService.getSessionToken());
      this.gitClient.getDirectory(request, {}, (err, response) => {
        if (err) {
          console.error(err);
          return;
        }
        console.log(response);
        this.files = response.getFilesList();
      });
    });
  }

}
