import { Component, OnInit } from '@angular/core';
import {FormValidationService} from '../../form-validation-service/form-validation.service';
import {PropertyMap} from '../../form-module/schema';
import {UserServiceClient} from '../../grpc/user_grpc_web_pb';
import {SessionService} from '../../session.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {AddSshPublicKeyRequest, DeleteSshPublicKeyRequest, GetSshPublicKeyRequest, SshPublicKey} from '../../grpc/user_pb';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.sass']
})
export class ConfigurationComponent implements OnInit {
  request = new AddSshPublicKeyRequest();
  userConstraints: PropertyMap;
  private sshPublicKeys: Array<SshPublicKey>;
  private username: string;

  constructor(
    private formValidationService: FormValidationService,
    private userServiceClient: UserServiceClient,
    private sessionService: SessionService,
    private route: ActivatedRoute,
  ) {
    this.userConstraints = formValidationService.user;
  }

  ngOnInit() {
    // TODO: navigation guard
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.username = this.route.parent.snapshot.paramMap.get('username');
      const request = new GetSshPublicKeyRequest();
      request.setSessiontoken(this.sessionService.getSessionToken());
      request.setUsername(this.username);
      this.userServiceClient.getSshPublicKeys(request, {}, (err, response) => {
        if (err) {
          console.error(err);
          return;
        }
        this.sshPublicKeys = response.getSshpublickeysList();
      });
    });
  }

  submit() {
    this.request.setSessiontoken(this.sessionService.getSessionToken());
    this.request.setUsername(this.username);
    this.userServiceClient.addPublicSshKey(this.request, {}, (err, response) => {
      if (err) {
        console.error(err);
        return;
      }
      // TODO: add SSH key to array
      //this.sshPublicKeys.push(response.getSshpublickeysList());
    });
  }

  delete(key: SshPublicKey, i: number) {
    const request = new DeleteSshPublicKeyRequest();
    request.setSessiontoken(this.sessionService.getSessionToken());
    request.setSshpublickeyid(key.getId());

    this.userServiceClient.deleteSshPublicKey(request, {}, (err, response) => {
      if (err) {
        console.error(err);
        return;
      }
      // TODO: add SSH key to array
      this.sshPublicKeys.splice(i, 1);
    });
  }
}
