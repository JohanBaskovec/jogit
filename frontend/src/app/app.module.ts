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
import {InputComponent} from './form-module/input/input.component';
import {FormGroupComponent} from './form-module/form-group/form-group.component';
import {InputLikeComponent} from './form-module/input-like/input-like.component';
import {ButtonComponent} from './form-module/button/button.component';
import {ErrorpageComponent} from './errorpage/errorpage.component';
// @ts-ignore
import schema from '../../../backend/src/main/resources/validation/build/schema.json';
import {FormValidationService} from './form-validation-service/form-validation.service';
import {GitRepositoryServiceClient} from './grpc/git-repository_grpc_web_pb';
import {UserPageModule} from './user-page/user-page.module';
import {CreateRepositoryComponent} from './create-repository/create-repository.component';
import {TextAreaComponent} from './form-module/textarea/text-area.component';
import {TextAreaFormGroupComponent} from './form-module/textarea-form-group/text-area-form-group.component';
import {AppFormModule} from './form-module/app-form.module';
import {UserServiceClient} from './grpc/user_grpc_web_pb';

const applicationConfiguration = new ApplicationConfiguration();

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    ErrorpageComponent,
    CreateRepositoryComponent,
  ],
  imports: [
    BrowserModule,
    UserPageModule,
    AppRoutingModule,
    FormsModule,
    AppFormModule
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
      provide: UserServiceClient,
      useValue: new UserServiceClient(applicationConfiguration.getBackendUrl())
    },
    {
      provide: FormValidationService, useValue: new FormValidationService(schema)
    },

  ],
  exports: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
