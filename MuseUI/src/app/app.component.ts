import { Component } from '@angular/core';
import * as $ from 'jquery';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MuseUI';
  status:boolean=false;
  token:any
  constructor(private aroute:ActivatedRoute,private route:Router) { }

  ngOnInit() {
    $(window).mousemove(function(e) {
      var change;
      var xpos=e.clientX;var ypos=e.clientY;var left= change*20;
      xpos=xpos*2;ypos=ypos*2;
      $('.new-slide-3 img').css('top',((100+(ypos/50))+"px"));
      $('.new-slide-3 img').css('right',((0+(xpos/80))+"px"));
                 
  }); 
  this.token=localStorage.getItem('jwt_token')
  console.log(this.token)
  if(this.token != null){
    this.status=true
  }
  }

  Logout(){
    localStorage.removeItem("jwt_token")
    this.status=false
    this.route.navigate(['/login']);
    
  }

}
