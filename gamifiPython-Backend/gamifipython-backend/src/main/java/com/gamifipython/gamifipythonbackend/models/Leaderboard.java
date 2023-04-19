package com.gamifipython.gamifipythonbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "leaderboard")
@Data
public class Leaderboard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "leaderboardId")
  private int leaderboardId;

  @Column(name = "leaderboardName", nullable = false)
  private String leaderboardName;

}
