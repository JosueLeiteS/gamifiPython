package com.gamifipython.gamifipythonbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "leaderboardentry")
@Data
public class LeaderboardEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "leaderboardEntryId")
  private int leaderboardEntryId;

  @Column(name = "studentId", nullable = false)
  private int studentId;

  @Column(name = "leaderboardId", nullable = false)
  private int leaderboardId;

  @Column(name = "leaderboardEntryScore", nullable = false)
  private int leaderboardEntryScore;

  @Column(name = "leaderboardEntryStudentName", nullable = false)
  private String leaderboardEntryStudentName;

}
