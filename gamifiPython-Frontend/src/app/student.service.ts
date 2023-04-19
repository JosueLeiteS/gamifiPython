import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private baseURL = 'http://localhost:8080/api/student';
  constructor(private httpClient: HttpClient) {}

  getStudentByEmail(email: String): Observable<Student> {
    return this.httpClient.get<Student>(`${this.baseURL}/${email}`);
  }

  updateStudent(email: String, student: Student): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${email}`, student);
  }
}
