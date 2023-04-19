import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StudentBadge } from './student-badge';

@Injectable({
  providedIn: 'root',
})
export class StudentBadgeService {
  private baseURL = 'http://localhost:8080/api/studentBadge';
  constructor(private httpClient: HttpClient) {}

  createStudentBadge(studentBadge: StudentBadge) {
    return this.httpClient.post(`${this.baseURL}`, studentBadge);
  }

  getStudentBadges(studentId: number) {
    return this.httpClient.get<StudentBadge[]>(`${this.baseURL}/${studentId}`);
  }
}
