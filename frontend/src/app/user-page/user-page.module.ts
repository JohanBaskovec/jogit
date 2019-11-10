
// @ts-ignore
import {NgModule} from "@angular/core";
import {RepositoriesComponent} from "./repositories/repositories.component";
import {UserPage} from "./user-page.component";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {FormsModule} from "@angular/forms";
import {UserPageRoutingModule} from "./user-page-routing.module";

@NgModule({
  declarations: [
    UserPage,
    RepositoriesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    UserPageRoutingModule,
  ],
  providers: [

  ],
})
export class UserPageModule {
}
