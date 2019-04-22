import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Observable } from 'rxjs';
import {retry} from 'rxjs/operators'
import { MuseManager } from '../MuseManager';

@Injectable({
  providedIn: 'root'
})
export class MuseService {

  tmEndPoint:string;
  apiKey:string;
  springEndPoint:string;
  
  constructor(private http:HttpClient) {

    this.tmEndPoint= 'https://www.themuse.com/api/public/jobs';
    this.apiKey = 'cb0203c7e50b457a66ffaef4a3a975f96e5ea5dc2442b9cbdbc888e336d04f0d';
    this.springEndPoint='http://localhost:1112/api/jobservice'

   }


   getJob(page):Observable<Array<MuseManager>>
   {
     console.log(page)
    const endPoint = `${this.tmEndPoint}?page=${page}&api_key=${this.apiKey}`;
    return this.http.get(endPoint).pipe(
      retry(3),
      map(this.pickJobResults),
      map(this.transform.bind(this))
    );

   }

   getJobById(id):Observable<MuseManager>
   {
     const endPoint = `${this.tmEndPoint}/${id}`;
     return this.http.get<MuseManager>(endPoint);
   }

   saveBookmarkedJob(muse)
   {
     return this.http.post(`${this.springEndPoint}/job`,muse);
   }

   getMyBookmarkedJob()
   {
     return this.http.get<Array<MuseManager>>(`${this.springEndPoint}/job`);
   }

   searchJobs(searchKey:string, page:number):any{
    const url = `${this.tmEndPoint}?category=${searchKey}&page=${page}`;
    return this.http.get(url).pipe(
      retry(3),
      map(this.pickJobResults), map(this.transform.bind(this))
    );

  }

   deleteJobFromBookmark(muse)

    {
      const url = `${this.springEndPoint}/job/${muse.id}/${muse.userId}`;
      return this.http.delete(url,{responseType:'text'});
    }

    updateRemarks(muse)
    {
      const url = `${this.springEndPoint}/job/${muse.id}`
      return this.http.put(url,muse)
    }

   pickJobResults(response)
   {
     return response['results'];
   }

   transform(muses) :Array<MuseManager>
   {
     return muses.map(muse => {
       return muse;
     });
    }
   }
