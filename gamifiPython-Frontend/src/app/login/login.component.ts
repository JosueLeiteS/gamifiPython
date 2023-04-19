import { Component, Inject, inject, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginStudentService } from '../login-student.service';
import { Student } from '../student';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  student: Student = new Student();
  studentLog: Student = new Student();

  constructor(
    private loginStudentService: LoginStudentService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  studentLogin() {
    console.log('STUDENT');
    console.log(this.student);
    this.loginStudentService.loginStudent(this.student).subscribe(
      (data) => {
        console.log('DATA');
        console.log(data);
        sessionStorage.setItem('student', this.student.email);
        this.toMain();
      },
      (error) => alert('Por favor, revise sus datos de ingreso.')
    );
  }

  toMain() {
    this.router.navigate(['/main']);
  }
}
