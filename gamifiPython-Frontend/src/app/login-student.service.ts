import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';

@Injectable({
  providedIn: 'root',
})
export class LoginStudentService {
  private baseURL = 'http://localhost:8080/api/login';
  constructor(private httpClient: HttpClient) {}

  public loginStudent(student: Student): Observable<object> {
    return this.httpClient.post(`${this.baseURL}`, student);
  }
}
