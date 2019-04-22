import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { JobRoutingModule } from './job-routing.module';
import { JobcontainerComponent } from './components/jobcontainer/jobcontainer.component';
import { MuseService } from './services/muse.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor.service';
import { BookmarkComponent } from './components/bookmark/bookmark.component';
import { MatPaginatorModule,MatAutocompleteModule, MatDividerModule } from '@angular/material';
import { JobdialogComponent } from './components/jobdialog/jobdialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule ,ReactiveFormsModule}    from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { SearchComponent } from './components/search/search.component';
import { JobdetailComponent } from './components/jobdetail/jobdetail.component';
import {MatCardModule} from '@angular/material'
import { AuthGuard } from 'src/app/auth.guard';
 

@NgModule({
  declarations: [JobcontainerComponent, BookmarkComponent, JobdialogComponent, SearchComponent, JobdetailComponent],
  imports: [
    CommonModule,
    JobRoutingModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    ReactiveFormsModule,MatAutocompleteModule, MatDividerModule,MatCardModule
  ],
  providers:[
    AuthGuard,
    MuseService,{
      provide:HTTP_INTERCEPTORS,
      useClass:TokenInterceptor,
      multi:true
    }
  ],
  entryComponents:[
    JobdialogComponent
  ]
})
export class JobModule { }
