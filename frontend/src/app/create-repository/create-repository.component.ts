import {Component, OnInit} from '@angular/core';
import {CreateGitRepositoryRequest} from '../grpc/git-repository_pb';
import {SessionService} from '../session.service';
import {Session} from '../grpc/session_pb';
import {GitRepositoryServiceClient} from '../grpc/git-repository_grpc_web_pb';
import {FormValidationService} from '../form-validation/form-validation-service/form-validation.service';
import {PropertyConstraints} from '../form-validation/property-constraints';

@Component({
  selector: 'app-create-repository',
  templateUrl: './create-repository.component.html',
  styleUrls: ['./create-repository.component.sass']
})
export class CreateRepositoryComponent implements OnInit {
  formSubmittedWithoutError: boolean;
  request: CreateGitRepositoryRequest = new CreateGitRepositoryRequest();
  error: boolean;
  constraints: { [p: string]: PropertyConstraints } = {};
  submitting: boolean;

  constructor(
    private sessionService: SessionService,
    private gitServiceClient: GitRepositoryServiceClient,
    private formValidationService: FormValidationService
  ) {
    this.constraints = formValidationService.createGitRepository;
  }

  ngOnInit() {
  }

  submit() {
    this.submitting = true;
    this.sessionService
      .getSession()
      .subscribe((session: Session) => {
        if (session == null || this.sessionService.sessionIsAnonymous(session)) {
          // should not be possible
          // TODO: add navigation guards
          return;
        }

        this.request.setSessiontoken(session.getId());
        this.gitServiceClient.create(this.request, {}, (err, response) => {
          this.submitting = false;
          if (err) {
            // TODO: error mangement
            this.error = true;
            return;
          }
          this.formSubmittedWithoutError = true;
        });

      });
  }
}
