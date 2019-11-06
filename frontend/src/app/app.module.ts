import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccountComponent } from './account/account.component';
import {RegisterClient} from "./grpc/register_grpc_web_pb";
import {LoginClient} from "./grpc/login_grpc_web_pb";
import {ApplicationConfiguration} from "./ApplicationConfiguration";
import {SessionServiceClient} from "./grpc/session_grpc_web_pb";

const applicationConfiguration = new ApplicationConfiguration();

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    {provide: RegisterClient, useValue: new RegisterClient(applicationConfiguration.getBackendUrl()) },
    {provide: LoginClient, useValue: new LoginClient(applicationConfiguration.getBackendUrl()) },
    {provide: SessionServiceClient, useValue: new SessionServiceClient(applicationConfiguration.getBackendUrl()) },

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
