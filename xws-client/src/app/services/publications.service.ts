import { API_MY_PUBLICATIONS, API_SEARCH_PUBLICATIONS, API_PUBLICATION, API_PUBLICATION_ALL, API_ADD_PUBLICATION, API_DELETE_PUBLICATION, API_REVISION, API_COVER_LETTER, API_COVER_LETTER_SUBMIT, API_METADATA_SEARCH } from './../config/api-paths';
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

  searchPublicationsMetadata(searchObject: any): Observable<any> {
    return this.http.post( API_METADATA_SEARCH, searchObject);
  }

  addPublication(xmlData: string): Observable<any> {
    return this.http.post(API_ADD_PUBLICATION, xmlData);
  }

  deletePublication(id: string): Observable<any> {
    return this.http.delete(`${API_DELETE_PUBLICATION}/${id}`);
  }

  addRevision(id: string, xml: string): Observable<any> {
    return this.http.put(API_REVISION + id, xml);
  }

  addCoverLetter(id: string, xml: string): Observable<any> {
    return this.http.post(API_COVER_LETTER + id, xml);
  }

  submitCoverLetter(id: string, xml: string): Observable<any> {
    return this.http.post(API_COVER_LETTER_SUBMIT + id, xml);
  }
}
