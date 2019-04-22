import { Component, OnInit } from '@angular/core';
import { MuseService } from '../../services/muse.service';
import { MuseManager } from '../../MuseManager';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog'
import { JobdialogComponent } from '../jobdialog/jobdialog.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'job-bookmark',
  templateUrl: './bookmark.component.html',
  styleUrls: ['./bookmark.component.css']
})
export class BookmarkComponent implements OnInit {

  jobs:Array<MuseManager>
  constructor(private museservice:MuseService,private snackBar:MatSnackBar,private dialog:MatDialog) { 
    this.jobs=[]
  }

  ngOnInit() {
    let message='watch List is Empty'
    this.museservice.getMyBookmarkedJob().subscribe((job) => {
      if(job.length===0){
        this.snackBar.open(message,'',{
          duration: 1000
        });
      }
      console.log(job)
      this.jobs.push(...job);
    })

  
  }



  updateFromWatchList(actionType,job){
    console.log('job is getting updated')
    let dialogRef = this.dialog.open(JobdialogComponent,{
      width: '400px',
      data: { obj: job, actionType:actionType }
    })
    console.log('open dialog')
    dialogRef.afterClosed().subscribe(result => {
      console.log("The Dialog was closed")
    })
  }

  deleteFromWatchList(job){
    let message = `${job.name} deleted from your bookmark`
    for(var i=0;i< this.jobs.length;i++){
      if(this.jobs[i].name === job.name){
        this.jobs.splice(i,1);
      }
    }
    this.museservice.deleteJobFromBookmark(job).subscribe((job) =>{
      this.snackBar.open(message,'',{
        duration: 1000
      })
    })
  }

}
