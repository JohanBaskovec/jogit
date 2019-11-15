// @ts-ignore
import {NgModule} from '@angular/core';
import {RepositoriesComponent} from './repositories/repositories.component';
import {UserPageComponent} from './user-page.component';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from '../app-routing.module';
import {FormsModule} from '@angular/forms';
import {UserPageRoutingModule} from './user-page-routing.module';
import {DirectoryComponent} from './directory/directory.component';
import {ConfigurationComponent} from './configuration/configuration.component';
import {AppModule} from '../app.module';
import {AppFormModule} from '../form-module/app-form.module';

@NgModule({
  declarations: [
    UserPageComponent,
    RepositoriesComponent,
    DirectoryComponent,
    ConfigurationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    UserPageRoutingModule,
    AppFormModule
  ],
  providers: [],
})
export class UserPageModule {
}
