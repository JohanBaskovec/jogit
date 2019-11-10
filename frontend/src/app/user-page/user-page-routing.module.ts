import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserPage} from "./user-page.component";
import {RepositoriesComponent} from "./repositories/repositories.component";

const routes: Routes = [
  {
    path: 'git/:username',
    component: UserPage,
    children: [
      {
        path: '',
        component: RepositoriesComponent,

      }
    ]
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserPageRoutingModule {
}
