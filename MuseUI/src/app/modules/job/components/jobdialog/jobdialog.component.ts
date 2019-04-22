import { Component, OnInit,Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MuseService } from '../../services/muse.service';
import { MuseManager } from '../../MuseManager';

@Component({
  selector: 'job-jobdialog',
  templateUrl: './jobdialog.component.html',
  styleUrls: ['./jobdialog.component.css']
})
export class JobdialogComponent implements OnInit {


  job: MuseManager;
  alert: string;
  actionType: string;
  constructor(private snacBar: MatSnackBar, private dialogRef: MatDialogRef<JobdialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MuseService) {

      this.alert = data.obj.alert;
      this.job = data.obj;
      this.actionType = data.actionType;
   }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  updateComments() {
    this.job.alert = this.alert;
    this.dialogRef.close();
    this.movieService.updateRemarks(this.job).subscribe(() => {
      this.snacBar.open("Job updated to Bookmark successfully", "", {
        duration: 2000
      });
    })
  }

}
