import {Component, ViewChild} from '@angular/core';
import {LoginClient} from '../grpc/login_grpc_web_pb';
import {LoginReply, LoginRequest} from '../grpc/login_pb';
import * as grpcWeb from 'grpc-web';
import {SessionService} from '../session.service';
import {FormValidationService} from '../form-validation/form-validation-service/form-validation.service';
import {NgForm} from '@angular/forms';
import {InputComponent} from '../input/input.component';
import {Status, StatusCode} from 'grpc-web';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent {
  formRequest: LoginRequest = new LoginRequest();
  formSubmittedWithoutError = false;
  submitting: boolean;
  @ViewChild('username')
  usernameComponent: InputComponent;
  @ViewChild('password')
  passwordComponent: InputComponent;


  @ViewChild('form')
  form: NgForm;
  private wrongUsernameOrPassword = false;

  constructor(
    private loginClient: LoginClient,
    private sessionService: SessionService,
    private formValidationService: FormValidationService,
    private router: Router,
  ) {

  }

  submit() {
    this.submitting = true;
    console.log('call');
    const stream = this.loginClient.login(
      this.formRequest,
      {},
      () => {
      }
    );
    stream.on('data', (response: LoginReply) => {
      this.sessionService.setSession(response.getSession());
      this.router.navigate(['git', response.getSession().getUserusername()]);
    });
    stream.on('error', (error: grpcWeb.Error) => {
      if (error.code === StatusCode.UNAUTHENTICATED) {
        this.wrongUsernameOrPassword = true;
        this.usernameComponent.control.setErrors({
          other: [
            'Invalid username or password.'
          ]
        });
        // force the input to be invalid without any specific error message
        this.passwordComponent.control.setErrors({});
      }
      console.log(error);
      this.submitting = false;
    });
    stream.on('status', (status: Status) => {
      console.log(status);
    });
    stream.on('end', () => {
      this.submitting = false;
      console.log('end');
    });

  }

  onUsernameChange(username: string) {
    this.formRequest.setUsername(username);
    this.onChange();
  }

  onPasswordChange(password: string) {
    this.formRequest.setPassword(password);
    this.onChange();
  }

  private onChange() {
    if (this.wrongUsernameOrPassword) {
      // if the form has been sent with invalid username/password,
      // we force both input to become invalid, so after changing
      // the value again we force the validation to run again.
      this.usernameComponent.control.updateValueAndValidity();
      this.passwordComponent.control.updateValueAndValidity();
    }
  }
}
