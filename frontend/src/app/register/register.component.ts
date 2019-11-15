import {Component} from '@angular/core';
import {RegisterReply, RegisterRequest} from "../grpc/register_pb";
import {RegisterClient} from "../grpc/register_grpc_web_pb";
import * as grpcWeb from 'grpc-web';
import {FormValidationService} from "../form-validation-service/form-validation.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass']
})
export class RegisterComponent {
  serverResponse: string = "";
  serverError: grpcWeb.Error = null;
  formRequest: RegisterRequest = new RegisterRequest();
  submitting: boolean;
  formSubmittedWithoutError: boolean = false;

  constructor(
    private registerClient: RegisterClient,
    private formValidationService: FormValidationService
  ) {
    console.log(formValidationService);
  }

  submit() {
    this.submitting = true;
    this.registerClient.register(this.formRequest, {}, (err: grpcWeb.Error, response: RegisterReply) => {
      if (err) {
        this.submitting = false;
        this.serverError = err;
        return;
      }
      this.formSubmittedWithoutError = false;
    })
  }

}
