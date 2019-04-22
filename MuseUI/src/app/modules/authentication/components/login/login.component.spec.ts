import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {of} from 'rxjs';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule, MatCardModule } from '@angular/material';
import { MatInputModule } from '@angular/material';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { By } from '@angular/platform-browser';

import { LoginComponent } from './login.component';
import { AuthenticationService } from '../../authentication.service';
import { AppComponent } from '../../../../app.component';

const testConfig = {
  userData: {
    email: 'test',
    name: 'testLast',
    userId: 'testUser',
    password: 'testPass'
  }
}

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthenticationService;
  let spyUser: any;
  let routes: Router;
  let location: Location;

  class AuthServiceStub {
    currentUser: any;
    
    constructor() {

     }

    login(credentials) {
      if(credentials.userId == testConfig.userData.userId) {
        console.log('data:::', this.currentUser);
        return of(credentials.userId);
      } else {
        return of(false);
      }
    }
  }

  class dummy {

  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [FormsModule, MatFormFieldModule, MatInputModule,
        MatButtonModule, BrowserAnimationsModule, HttpClientModule,MatCardModule,
        RouterTestingModule.withRoutes(
          [{path: '', component: dummy}]
        )
      ],
      providers: [AppComponent,{provide: AuthenticationService, useClass: AuthServiceStub}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    routes = TestBed.get(Router);
    fixture = TestBed.createComponent(LoginComponent);
    location = TestBed.get(Location);
    component = fixture.componentInstance;
    fixture.detectChanges();
    fixture.debugElement.injector.get(AppComponent);
    fixture.debugElement.injector.get(AuthenticationService);
  });

  it('should create app component', async(() => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it('should contain two input box for userId and password', () => {
    let userId = fixture.debugElement.query(By.css('.inputId'));
    let password = fixture.debugElement.query(By.css('.inputPass'));
    let userButton = fixture.debugElement.query(By.css('.login-user'));

    let userIdInput = userId.nativeElement;
    let passwordInput = password.nativeElement;
    let userButtonInput = userButton.nativeElement;

    expect(userIdInput).toBeTruthy();
    expect(passwordInput).toBeTruthy();
    expect(userButtonInput).toBeTruthy();
  });

  it('should redirect to login if registered successfully', async(() => {
    let userId = fixture.debugElement.query(By.css('.inputId'));
    let password = fixture.debugElement.query(By.css('.inputPass'));
    let userButton = fixture.debugElement.query(By.css('.login-user'));

    let userIdInput = userId.nativeElement;
    let passwordInput = password.nativeElement;
    let userButtonInput = userButton.nativeElement;
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      userIdInput.value = 'testuser';
      passwordInput.value = 'testpass';
      userIdInput.dispatchEvent(new Event('inptut'));
      passwordInput.dispatchEvent(new Event('inptut'));
      userButtonInput.click();
    }).then(() => {
      expect(location.path()).toBe('');
    });
  }));
});

