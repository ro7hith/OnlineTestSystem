import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuizserviceService {
  private baseUrl = "http://localhost:8081/api/assess";
  private userScore: number = 0;
  private totalScore: number = 0;

  constructor(private httpClient: HttpClient) {}

 
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getScores(assessmentId: number): Observable<string> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<string>(`${this.baseUrl}/getScores/${assessmentId}`, { headers });
  }

  getStats(assessmentId: number): Observable<string> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<string>(`${this.baseUrl}/getStats/${assessmentId}`, { headers });
  }
}
