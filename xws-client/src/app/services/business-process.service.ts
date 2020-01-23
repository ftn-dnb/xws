import { API_BUSINESS_PROCESSES } from './../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BusinessProcessService {

  constructor(private http: HttpClient) { 
  }

  getAllProcesses(): Observable<any> {
    return this.http.get(API_BUSINESS_PROCESSES);
  }
}
