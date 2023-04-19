package com.gamifipython.gamifipythonbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "badge")
@Data
public class Badge {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "badgeId")
  private int badgeId;

  @Column(name = "badgeName", nullable = false)
  private String badgeName;

  @Column(name = "badgeDescription", nullable = false)
  private String badgeDescription;

}
