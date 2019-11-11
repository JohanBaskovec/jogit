import {NgModule} from '@angular/core';
import {Route, RouterModule, Routes, UrlMatcher, UrlMatchResult, UrlSegment, UrlSegmentGroup} from '@angular/router';
import {UserPage} from './user-page.component';
import {RepositoriesComponent} from './repositories/repositories.component';
import {DirectoryComponent} from './directory/directory.component';

const directoryPathMatcher: UrlMatcher = (segments: UrlSegment[], group: UrlSegmentGroup, route: Route): UrlMatchResult => {
  const length = segments.length;

  console.log(segments);
  const repositoryNameSegment: UrlSegment = new UrlSegment(segments[0].path, {});
  let directoryPath = '';
  for (let i = 1; i < segments.length; i++) {
    directoryPath += `${segments[i].path}/`;
  }
  const urlSegment: UrlSegment = new UrlSegment(directoryPath, {});
  return {
    consumed: segments,
    posParams: {
      repositoryName: repositoryNameSegment,
      directoryPath: urlSegment,
    }
  };
};
const routes: Routes = [
  {
    path: 'git/:username',
    component: UserPage,
    children: [
      {
        path: '',
        component: RepositoriesComponent,
      },
      {
        matcher: directoryPathMatcher,
        component: DirectoryComponent,
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
