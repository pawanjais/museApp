import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest,HttpEvent,HttpHandler } from '@angular/common/http';



import {Observable} from 'rxjs';
import { AuthenticationService } from '../authentication/authentication.service';
@Injectable({
  providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {
  
  
  
 

  constructor(private auth:AuthenticationService) { }


  intercept(req: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>> {
    console.log("In intercept");
 
    if(req.url.startsWith("https://www.themuse.com/"))
    {
      
 
    }else{
     req = req.clone({
 
 
       setHeaders:{
   
         Authorization: `Bearer ${this.auth.getToken()}`
       }
      });
    }
 
    return next.handle(req);
   }
}