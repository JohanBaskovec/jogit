import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AccountComponent} from './account/account.component';
import {RegisterClient} from './grpc/register_grpc_web_pb';
import {LoginClient} from './grpc/login_grpc_web_pb';
import {ApplicationConfiguration} from './ApplicationConfiguration';
import {SessionServiceClient} from './grpc/session_grpc_web_pb';
import {InputComponent} from './input/input.component';
import {FormGroupComponent} from './form-group/form-group.component';
import {InputLikeComponent} from './input-like/input-like.component';
import {ButtonComponent} from './button/button.component';
import {ErrorpageComponent} from './errorpage/errorpage.component';
// @ts-ignore
import schema from '../../../backend/src/main/resources/validation/build/schema.json';
import {FormValidationService} from './form-validation/form-validation-service/form-validation.service';
import {ObjectConstraints} from './form-validation/object-contraints';
import {GitRepositoryServiceClient} from './grpc/git-repository_grpc_web_pb';
import {UserPageModule} from './user-page/user-page.module';
import {CreateRepositoryComponent} from './create-repository/create-repository.component';

const applicationConfiguration = new ApplicationConfiguration();

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    InputComponent,
    FormGroupComponent,
    InputLikeComponent,
    ButtonComponent,
    ErrorpageComponent,
    CreateRepositoryComponent,
  ],
  imports: [
    BrowserModule,
    UserPageModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    {
      provide: RegisterClient,
      useValue: new RegisterClient(applicationConfiguration.getBackendUrl())
    },
    {
      provide: LoginClient,
      useValue: new LoginClient(applicationConfiguration.getBackendUrl())
    },
    {
      provide: SessionServiceClient,
      useValue: new SessionServiceClient(applicationConfiguration.getBackendUrl())
    },
    {
      provide: GitRepositoryServiceClient,
      useValue: new GitRepositoryServiceClient(applicationConfiguration.getBackendUrl())
    },
    {
      provide: FormValidationService, useValue: new FormValidationService(schema)
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
