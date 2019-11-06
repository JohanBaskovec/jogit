import {Component, OnInit} from '@angular/core';
import {LoginClient} from "../grpc/login_grpc_web_pb";
import {LoginReply, LoginRequest} from "../grpc/login_pb";
import * as grpcWeb from 'grpc-web';
import {SessionService} from "../session.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent {
  formRequest: LoginRequest = new LoginRequest();

  constructor(private loginClient: LoginClient, private sessionService: SessionService) {

  }

  submit() {
    this.loginClient.login(
      this.formRequest,
      {},
      (err: grpcWeb.Error, response: LoginReply) => {
        // TODO: error handling
        if (err) {
          return;
        }
        this.sessionService.setSession(response.getSession());
      }
    )
  }
}
