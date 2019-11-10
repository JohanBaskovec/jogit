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
      const path = params.get('directoryPath');
      const pathParts = path.split('/');
      const username = this.route.parent.snapshot.paramMap.get('username');
      const repositoryName = pathParts[0];
      let directoryPath = '';
      for (let i = 1; i < pathParts.length; i++) {
        directoryPath += `${pathParts[i]}/`;
      }
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
