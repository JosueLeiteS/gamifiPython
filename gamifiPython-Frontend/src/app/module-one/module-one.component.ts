import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LeaderboardEntryService } from '../leaderboard-entry.service';
import { LeaderboardEntry } from '../leaderboardEntry';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-module-one',
  templateUrl: './module-one.component.html',
  styleUrls: ['./module-one.component.css'],
})
export class ModuleOneComponent implements OnInit {
  leaderboardEntries: LeaderboardEntry[];
  leaderboardid: number = 1;
  img1 = 'assets/images/mod1_1.jpg';
  img2 = 'assets/images/mod1_2.png';
  img3 = 'assets/images/mod1_3.png';

  constructor(
    private studentService: StudentService,
    private leaderboardEntryService: LeaderboardEntryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.leaderboardEntries = [
      {
        leaderboardEntryId: 1,
        studentId: 1,
        leaderboardId: 1,
        leaderboardEntryScore: 20,
        leaderboardEntryStudentName: 'Joe Doe',
      },
    ];
    this.getLeaderboardList(1);
  }

  toMain() {
    this.router.navigate(['/main']);
  }

  toExam() {
    this.router.navigate(['exam/1']);
  }

  private getLeaderboardList(leaderboardId: number) {
    this.leaderboardEntryService
      .getLeaderboardEntries(leaderboardId)
      .subscribe((set) => {
        this.leaderboardEntries = set;
      });
  }
}
