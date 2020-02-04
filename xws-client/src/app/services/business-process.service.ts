import { Observable } from 'rxjs';
import {tap} from 'rxjs/operators';
import { API_BUSINESS_PROCESSES, API_SHOW_MY_REVIEW_REQUESTS, API_ACCEPT_REVIEW_REQUEST, API_DECLINE_REVIEW_REQUEST, API_ACCEPT_PUBLICATION, API_DECLINE_PUBLICATION, API_RECOMMEND_REVIEWERS, API_ADD_REVIEWERS, API_ADD_REVIEW, API_GET_REVIEW_PROCESS_ID, API_CHANGE_PHASE } from './../config/api-paths';
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
    return this.http.get(API_SHOW_MY_REVIEW_REQUESTS).pipe(
      tap(data => console.log(data))
    );
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

  addReview(id: string, xml: string): Observable<any> {
    return this.http.post(API_ADD_REVIEW, {xmlData: xml, processId: id});
  }

  getReviewsByProcessId(id: string): Observable<any> {
    return this.http.get(API_GET_REVIEW_PROCESS_ID + id);
  }

  changePhase(processId: string, phaseChange: string): Observable<any> {
    return this.http.put(API_CHANGE_PHASE + processId, phaseChange);
  }
}
