package com.gamifipython.gamifipythonbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifipython.gamifipythonbackend.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
  Student findByEmail(String email);
}
