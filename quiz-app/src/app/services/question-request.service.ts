import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuestionRequest } from '../classes/question-request';

@Injectable({
  providedIn: 'root'
})
export class QuestionRequestService {
  private baseUrl = 'http://localhost:8081/api/questions';

  constructor(private http: HttpClient) { }

  sendQuizData(qrequest: QuestionRequest): Observable<any> {
    const token = localStorage.getItem('token'); 

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(this.baseUrl + "/getAll", qrequest, { headers });
  }
}
