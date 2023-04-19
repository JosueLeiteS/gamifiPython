import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css'],
})
export class MainMenuComponent implements OnInit {
  activeStudent: Student = new Student();
  email: String;

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit(): void {
    if (sessionStorage.getItem('student') !== null) {
      this.email = sessionStorage.getItem('student')!;
    } else {
      this.toLogin();
    }

    this.studentService.getStudentByEmail(this.email).subscribe(
      (data) => {
        this.activeStudent = data;
        console.log('this.activeStudent');
        console.log(this.activeStudent);
        // Escribir resto del codigo aqui
      },
      (error) => {
        console.log(error);
      }
    );
  }

  toLogin() {
    sessionStorage.clear();
    this.router.navigate(['']);
  }

  toModule(moduelId: number) {
    this.router.navigate(['module', moduelId]);
  }
}
