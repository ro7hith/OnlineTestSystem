import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AssessmentDetailsDto } from '../classes/assessment-details-dto';

@Injectable({
  providedIn: 'root'
})
export class AssessmentDetailsService {

  private baseUrl = 'http://localhost:8081/api/assess';

  constructor(private http: HttpClient) {}

  getAssessmentDetails(assessmentId: number): Observable<AssessmentDetailsDto[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get<AssessmentDetailsDto[]>(`${this.baseUrl}/details/${assessmentId}`, { headers });
  }
}
