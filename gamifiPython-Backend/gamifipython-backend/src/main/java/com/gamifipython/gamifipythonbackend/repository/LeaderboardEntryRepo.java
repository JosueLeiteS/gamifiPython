package com.gamifipython.gamifipythonbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifipython.gamifipythonbackend.models.LeaderboardEntry;

@Repository
public interface LeaderboardEntryRepo extends JpaRepository<LeaderboardEntry, Long> {

  List<LeaderboardEntry> findAllByLeaderboardIdOrderByLeaderboardEntryStudentNameAsc(int leaderboardId);

  LeaderboardEntry findByLeaderboardIdAndStudentId(int leaderboardId, int studentId);

  boolean existsByLeaderboardIdAndStudentId(int leaderboardId, int studentId);

}
