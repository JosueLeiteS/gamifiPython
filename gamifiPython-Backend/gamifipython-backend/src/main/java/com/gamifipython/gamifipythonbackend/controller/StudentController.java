package com.gamifipython.gamifipythonbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifipython.gamifipythonbackend.repository.StudentRepo;
import com.gamifipython.gamifipythonbackend.models.Student;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController {

  @Autowired
  private StudentRepo studentRepo;

  @GetMapping("/student")
  public List<Student> listStudents() {
    return studentRepo.findAll();
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginStudent(@RequestBody Student studentData) {
    Student student = studentRepo.findByEmail(studentData.getEmail());
    if (student.getPass().equals(studentData.getPass())) {
      return ResponseEntity.ok(student);
    }
    return (ResponseEntity<?>) ResponseEntity.internalServerError();
  }

  @GetMapping("/student/{email}")
  public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
    Student student = studentRepo.findByEmail(email);
    return ResponseEntity.ok(student);
  }

  @PutMapping("/student/{email}")
  public ResponseEntity<Student> updateStudent(@PathVariable String email, @RequestBody Student studentData) {
    Student student = studentRepo.findByEmail(email);

    student.setPoints(studentData.getPoints());
    student.setLevel(studentData.getLevel());

    Student updatedStudent = studentRepo.save(student);
    return ResponseEntity.ok(updatedStudent);
  }

}
