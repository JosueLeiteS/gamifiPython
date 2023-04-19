import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LeaderboardEntry } from './leaderboardEntry';

@Injectable({
  providedIn: 'root',
})
export class LeaderboardEntryService {
  private baseURL = 'http://localhost:8080/api/leaderboardEntry';
  constructor(private httpClient: HttpClient) {}

  createLeaderboardEntry(leaderboardEntry: LeaderboardEntry) {
    return this.httpClient.post(`${this.baseURL}`, leaderboardEntry);
  }

  getLeaderboardEntries(leaderboardId: number) {
    return this.httpClient.get<LeaderboardEntry[]>(
      `${this.baseURL}/${leaderboardId}`
    );
  }
}
