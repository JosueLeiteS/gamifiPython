package com.gamifipython.gamifipythonbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifipython.gamifipythonbackend.models.StudentBadge;

@Repository
public interface StudentBadgeRepo extends JpaRepository<StudentBadge, Long> {

  List<StudentBadge> findAllByStudentId(int studentId);

  boolean existsByBadgeIdAndStudentId(int badgeId, int studentId);
}
