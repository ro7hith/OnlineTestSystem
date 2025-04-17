import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserTopicAverage } from '../classes/user-topic-average';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class StatsService {
  private apiUrl = 'http://localhost:8081/api/assess/usertopicaverages';

  constructor(private http: HttpClient) {}

  getUserStats(email: string): Observable<UserTopicAverage[]> {
    const token = localStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
  
    return this.http.get<UserTopicAverage[]>(
      `${this.apiUrl}?email=${email}`,
      { headers }
    );
  }
}  
