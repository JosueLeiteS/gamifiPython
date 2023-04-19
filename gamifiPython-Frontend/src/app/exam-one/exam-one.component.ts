import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { LeaderboardEntryService } from '../leaderboard-entry.service';
import { LeaderboardEntry } from '../leaderboardEntry';
import { Student } from '../student';
import { StudentBadge } from '../student-badge';
import { StudentBadgeService } from '../student-badge.service';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-exam-one',
  templateUrl: './exam-one.component.html',
  styleUrls: ['./exam-one.component.css'],
})
export class ExamOneComponent implements OnInit {
  examForm: FormGroup;
  answer: number[] = [];
  check: number[] = [2, 3, 2, 1, 4];
  points: number = 0;
  rightAnswer: number = 0;
  email: String;
  leaderboardId: number = 1;
  leaderboardEntry: LeaderboardEntry = new LeaderboardEntry();
  badgeComplete: StudentBadge = new StudentBadge();
  badgePerfect: StudentBadge = new StudentBadge();
  activeStudent: Student = new Student();

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private studentService: StudentService,
    private leaderboardEntryService: LeaderboardEntryService,
    private studentBadgeService: StudentBadgeService
  ) {}

  // Cuestionario
  ngOnInit(): void {
    this.examForm = this.formBuilder.group({
      p1: ['', [Validators.required]],
      p2: ['', [Validators.required]],
      p3: ['', [Validators.required]],
      p4: ['', [Validators.required]],
      p5: ['', [Validators.required]],
    });

    this.getUserByEmail();
    this.examForm.valueChanges.subscribe(console.log);
  }

  finishExam() {
    this.saveAnswers();
    this.calculatePoints();

    //Update user points and level
    this.activeStudent.points = this.activeStudent.points + this.points;
    if (this.activeStudent.points >= (this.activeStudent.level + 1) * 100) {
      this.activeStudent.level = this.activeStudent.level + 1;
    }

    this.studentService
      .updateStudent(this.activeStudent.email, this.activeStudent)
      .subscribe(
        (data) => {
          //Continua codigo
          this.leaderboardEntry.leaderboardId = this.leaderboardId;
          this.leaderboardEntry.studentId = this.activeStudent.studentId;
          this.leaderboardEntry.leaderboardEntryScore = this.points;
          this.leaderboardEntry.leaderboardEntryStudentName =
            this.activeStudent.firstName + ' ' + this.activeStudent.lastName;

          this.badgeComplete.badgeId = 1;
          this.badgeComplete.studentId = this.activeStudent.studentId;

          this.badgePerfect.badgeId = 2;
          this.badgePerfect.studentId = this.activeStudent.studentId;

          this.leaderboardEntryService
            .createLeaderboardEntry(this.leaderboardEntry)
            .subscribe(
              (leaderboardData) => {
                console.log(leaderboardData);

                if (this.points == 50) {
                  this.studentBadgeService
                    .createStudentBadge(this.badgePerfect)
                    .subscribe((badge) => {
                      console.log(badge);
                    });
                }

                if (this.points >= 30) {
                  this.studentBadgeService
                    .createStudentBadge(this.badgeComplete)
                    .subscribe((completeBadge) => {
                      console.log(completeBadge);
                    });
                }

                //Return routing
                this.returntoModule();
              },
              (error) => {
                console.log(error);
              }
            );
        },
        (error) => {
          console.log(error);
        }
      );
  }

  saveAnswers() {
    this.answer.push(this.examForm.get('p1')?.value);
    this.answer.push(this.examForm.get('p2')?.value);
    this.answer.push(this.examForm.get('p3')?.value);
    this.answer.push(this.examForm.get('p4')?.value);
    this.answer.push(this.examForm.get('p5')?.value);
  }

  calculatePoints() {
    for (var index in this.check) {
      if (this.check[index] == this.answer[index]) {
        this.rightAnswer = this.rightAnswer + 1;
      }
    }
    this.points = this.rightAnswer * 10;
  }

  getUserByEmail() {
    this.email = sessionStorage.getItem('student')!;
    this.studentService.getStudentByEmail(this.email).subscribe(
      (data) => {
        this.activeStudent = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  returntoModule() {
    this.router.navigate(['module', 1]);
  }
}
