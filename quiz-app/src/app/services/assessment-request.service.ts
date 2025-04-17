import { Injectable } from '@angular/core';
import { QuestionRequest } from '../classes/question-request';
import { Observable } from 'rxjs';
import { AssessmentRequest } from '../classes/assessment-request';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AssessmentRequestService {
  private baseUrl = "http://localhost:8081/api/assess";

  constructor(private http: HttpClient) { }

  startAssessment(assessmentRequest: AssessmentRequest): Observable<any> {
    const token = localStorage.getItem('token');  
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(`${this.baseUrl}/start`, assessmentRequest, { headers });
  }
}


