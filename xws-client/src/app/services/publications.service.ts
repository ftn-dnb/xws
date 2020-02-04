import { API_MY_PUBLICATIONS, API_SEARCH_PUBLICATIONS, API_PUBLICATION, API_PUBLICATION_ALL, API_ADD_PUBLICATION, API_DELETE_PUBLICATION } from './../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PublicationsService {

  constructor(private http: HttpClient) {
  }

  getPublications(): Observable<any> {
    return this.http.get(API_PUBLICATION_ALL);
  }

  getMyPublications(): Observable<any> {
    return this.http.get(API_MY_PUBLICATIONS);
  }

  getPublication(id: string): Observable<any> {
    return this.http.get(`${API_PUBLICATION}/${id}`);
  }

  searchPublications(searchObject: any): Observable<any> {
    return this.http.post(API_SEARCH_PUBLICATIONS, searchObject);
  }

  addPublication(xmlData: string): Observable<any> {
    return this.http.post(API_ADD_PUBLICATION, xmlData);
  }

  deletePublication(id: string): Observable<any> {
    return this.http.delete(`${API_DELETE_PUBLICATION}/${id}`);
  }
}
