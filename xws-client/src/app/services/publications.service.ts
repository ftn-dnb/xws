import { API_MY_PUBLICATIONS, API_SEARCH_PUBLICATIONS, API_PUBLICATION } from './../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PublicationsService {

  constructor(private http: HttpClient) { 
  }

  getMyPublications(): Observable<any> {
    return this.http.get(API_MY_PUBLICATIONS);
  }

  getPublication(id: string): Observable<any> {
    return this.http.get(`${API_PUBLICATION}/${id}`);
  }

  searchPublications(): Observable<any> {
    // TODO: kasnije dodati parametar koji sadrzi sta treba filtrirati/traziti

    return this.http.get(API_SEARCH_PUBLICATIONS);
  }
}
