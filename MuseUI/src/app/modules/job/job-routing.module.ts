import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JobcontainerComponent } from './components/jobcontainer/jobcontainer.component';
import { BookmarkComponent } from './components/bookmark/bookmark.component';
import { SearchComponent } from './components/search/search.component';
import { JobdetailComponent } from './components/jobdetail/jobdetail.component';
import { AuthGuard } from 'src/app/auth.guard';

const jobroutes: Routes = [
  {
    path: 'job', 
    canActivate:[AuthGuard], 
    children: [
      {
          path:'',
          redirectTo:'/job/all',

          pathMatch: 'full'
      },
      {
          path:'all',
          component: JobcontainerComponent
      },
      {
        path:'bookmark',
        component: BookmarkComponent
      },
      {
        path:'search',
        component: SearchComponent
     },{
      path:':id',
      component: JobdetailComponent
   }
  ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(jobroutes)],
  exports: [RouterModule]
})
export class JobRoutingModule { }
