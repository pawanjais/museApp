import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../authentication.service';
import { User } from '../../User';

@Component({
  selector: 'auth-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();
  constructor(private authService: AuthenticationService,private router: Router) { }


  ngOnInit() {
  }

  registerUser() {
    console.log("Register User data:", this.newUser);
    this.authService.registerUser(this.newUser).subscribe(data => {
      console.log("User registered", data);
      this.router.navigate(['/login']);
    });
  }

}
