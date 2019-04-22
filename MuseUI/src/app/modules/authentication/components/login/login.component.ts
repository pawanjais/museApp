import { Component, OnInit, Input } from '@angular/core';
import { User } from '../../User';
import { AuthenticationService } from '../../authentication.service';
import { Router } from '@angular/router';
import { AppComponent } from '../../../../app.component';


@Component({
  selector: 'auth-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User = new User();
  @Input()
  status:boolean;
  constructor(private authService: AuthenticationService, private router: Router, private appComponent:AppComponent) { }

  ngOnInit() {
  }

  loginUser() {
    console.log("Login user", this.user);
    this.authService.loginUser(this.user).subscribe(data => {
      console.log("Login successful");
      console.log(data['token']);
      if(data['token']) {
        this.authService.setToken(data['token']);
        
        this.appComponent.ngOnInit();
        this.router.navigate(['/job']);
        
      }
      
    });
  }

}
