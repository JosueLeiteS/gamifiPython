package com.gamifipython.gamifipythonbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifipython.gamifipythonbackend.models.StudentBadge;
import com.gamifipython.gamifipythonbackend.repository.StudentBadgeRepo;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentBadgeController {

  @Autowired
  private StudentBadgeRepo studentBadgeRepo;

  @GetMapping("/studentBadge")
  public List<StudentBadge> listAssignedBadges() {
    return studentBadgeRepo.findAll();
  }

  @GetMapping("/studentBadge/{studentId}")
  public List<StudentBadge> listAssignedBadgesOfStudent(@PathVariable int studentId) {
    return studentBadgeRepo.findAllByStudentId(studentId);
  }

  private boolean existsInStudentBadge(@PathVariable int badgeId, @PathVariable int studentId) {
    return (studentBadgeRepo.existsByBadgeIdAndStudentId(badgeId, studentId));
  }

  @PostMapping("/studentBadge")
  public StudentBadge createStudentBadge(@RequestBody StudentBadge studentBadge) {
    if (existsInStudentBadge(studentBadge.getBadgeId(), studentBadge.getStudentId())) {
      return null;
    } else {
      return studentBadgeRepo.save(studentBadge);
    }
  }

}
