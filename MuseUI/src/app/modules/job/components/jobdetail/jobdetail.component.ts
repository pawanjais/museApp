import { Component, OnInit } from '@angular/core';
import { MuseService } from '../../services/muse.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MuseManager } from '../../MuseManager';

@Component({
  selector: 'job-jobdetail',
  templateUrl: './jobdetail.component.html',
  styleUrls: ['./jobdetail.component.css']
})
export class JobdetailComponent implements OnInit {
  job:MuseManager;
  constructor(private museService:MuseService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.museService.getJobById(id).subscribe((job1) =>
    {
      console.log(job1);
        this.job= job1;

      
    })
  }

}
