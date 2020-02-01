import { Observable } from 'rxjs';
import { API_BUSINESS_PROCESSES, API_SHOW_MY_REVIEW_REQUESTS, API_ACCEPT_REVIEW_REQUEST, API_DECLINE_REVIEW_REQUEST, API_ACCEPT_PUBLICATION, API_DECLINE_PUBLICATION, API_RECOMMEND_REVIEWERS, API_ADD_REVIEWERS } from './../config/api-paths';
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

  acceptPublication(processId: string): Observable<any> {
    return this.http.get(`${API_ACCEPT_PUBLICATION}/${processId}`);
  }

  declinePublication(processId: string): Observable<any> {
    return this.http.get(`${API_DECLINE_PUBLICATION}/${processId}`);
  }

  recommendReviewers(processId: string): Observable<any> {
    return this.http.get(`${API_RECOMMEND_REVIEWERS}/${processId}`);
  }

  addReviewers(processId: string, userIds: string[]): Observable<any> {
    return this.http.put(`${API_ADD_REVIEWERS}/${processId}`, userIds);
  }
}
