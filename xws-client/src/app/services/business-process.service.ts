import { Observable } from 'rxjs';
import { API_BUSINESS_PROCESSES, API_SHOW_MY_REVIEW_REQUESTS, API_ACCEPT_REVIEW_REQUEST, API_DECLINE_REVIEW_REQUEST } from './../config/api-paths';
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

  getProcess(id: string): Observable<any> {
    return this.http.get(`${API_BUSINESS_PROCESSES}/${id}`);
  }

  getMyReviewRequests(): Observable<any> {
    return this.http.get(API_SHOW_MY_REVIEW_REQUESTS);
  }

  acceptReviewRequest(id: string): Observable<any> {
    return this.http.get(`${API_ACCEPT_REVIEW_REQUEST}/${id}`);
  }

  declineReviewRequest(id: string): Observable<any> {
    return this.http.get(`${API_DECLINE_REVIEW_REQUEST}/${id}`);
  }
}
