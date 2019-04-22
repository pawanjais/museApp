import { Component, OnInit } from '@angular/core';
import { MuseService } from '../../services/muse.service';
import { MuseManager } from '../../MuseManager';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'job-jobcontainer',
  templateUrl: './jobcontainer.component.html',
  styleUrls: ['./jobcontainer.component.css']
})
export class JobcontainerComponent implements OnInit {

  jobs:Array<MuseManager>
  length = 1000;
  pageSize = 10;

  constructor(private museservice:MuseService,private snackBar:MatSnackBar) {
    this.jobs=[]
   }

  ngOnInit() {
    this.museservice.getJob(1).subscribe((job) => {
      this.jobs.push(...job);
      console.log(this.jobs)
      this.jobs=this.jobs.splice(0,10);
      console.log(this.jobs)
    });
  }

  onPageChanged(e) {
    console.log(e)
    console.log(e.pageIndex)
    //this.activePageDataChunk = this.datasource.slice(firstCut, secondCut);
    this.jobs=[]
    this.museservice.getJob(e.pageIndex + 1).subscribe((job) => {
      this.jobs.push(...job);
      console.log(this.jobs)
      this.jobs=this.jobs.splice(0,10);
      console.log(this.jobs)
    });
  }

  addToBookmarkList(job){
    console.log("Job object",job)
    let message=`${job.name} add to bookmark`
    console.log("job name",job.name)
    this.museservice.saveBookmarkedJob(job).subscribe(() =>{
      this.snackBar.open(message,'' ,{
        duration: 1000
      })
    });
  }

}
