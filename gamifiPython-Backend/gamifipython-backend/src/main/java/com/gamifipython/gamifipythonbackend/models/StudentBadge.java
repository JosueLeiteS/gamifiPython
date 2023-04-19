package com.gamifipython.gamifipythonbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "studentbadge")
@Data
public class StudentBadge {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "studentBadgeId")
  private int studentBadgeId;

  @Column(name = "stundentId", nullable = false)
  private int studentId;

  @Column(name = "badgeId", nullable = false)
  private int badgeId;

}
