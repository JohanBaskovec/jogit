import { Component, OnInit } from '@angular/core';
import {RegisterReply, RegisterRequest} from "../grpc/register_pb";
import {RegisterClient} from "../grpc/register_grpc_web_pb";
import * as grpcWeb from 'grpc-web';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass']
})
export class RegisterComponent {
  serverResponse: string = "";
  serverError: grpcWeb.Error = null;
  formRequest: RegisterRequest = new RegisterRequest();

  constructor(private registerClient: RegisterClient) {

  }

  submit() {
    this.registerClient.register(this.formRequest, {}, (err: grpcWeb.Error, response: RegisterReply) => {
      if (err) {
        this.serverError = err;
        return;
      }
    })
  }

}
