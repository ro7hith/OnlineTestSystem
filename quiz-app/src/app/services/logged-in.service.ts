import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoggedInService {
  private baseUrl = "http://localhost:8081/auth/";

  constructor(private httpClient: HttpClient) {}

  register(user: any): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}register`, user);
  }

  validate(user: any): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}token`, user);
  }
}
