import { Component, OnInit } from '@angular/core';
import { MuseManager } from '../../MuseManager';
import { MuseService } from '../../services/muse.service';
import { MatSnackBar } from '@angular/material';
import { startWith, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'job-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  jobs:Array<MuseManager>;
  page:number;
  searchKey:string;
  
  constructor(private museervice:MuseService , private snackBar:MatSnackBar) { 
    this.jobs = [];
    this.page = 1;
  }

  myControl = new FormControl();
  options: string[] = ['Account Management', 'Creative & Design', 'Data Science','Education','Finance','Healthcare & Medicine',
                    'Legal','Operations','Retail','Social Media & Community','Business & Strategy','Customer Service',
                    'Editorial','Engineering','Fundraising & Development','HR & Recruiting','Marketing & PR','Project & Product Management','Sales'];
  filteredOptions: Observable<string[]>;
  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges
    .pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  onEnter(searchKey) {
    this.jobs=[];
    this.searchKey = searchKey;

    this.museervice.searchJobs(searchKey,this.page).subscribe((res) => {
  
      this.jobs.push(...res);
    });

  }

  updatePageNumber(page:number){
    this.page = page+1;
    this.onEnter(this.searchKey);
    
  }

  addToBookmarkList(job){
    console.log("Job object",job)
    let message=`${job.name} add to bookmark`
    console.log("job name",job.name)
    this.museervice.saveBookmarkedJob(job).subscribe(() =>{
      this.snackBar.open(message,'' ,{
        duration: 1000
      })
    });
  }

}
