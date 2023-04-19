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

import com.gamifipython.gamifipythonbackend.models.LeaderboardEntry;
import com.gamifipython.gamifipythonbackend.repository.LeaderboardEntryRepo;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class LeaderboardEntryController {

  @Autowired
  private LeaderboardEntryRepo leaderboardEntryRepo;

  @GetMapping("/leaderboardEntry")
  public List<LeaderboardEntry> listLeaderboardEntries() {
    return leaderboardEntryRepo.findAll();
  }

  private LeaderboardEntry createNewLeaderboardEntry(@RequestBody LeaderboardEntry leaderboardEntry) {
    return leaderboardEntryRepo.save(leaderboardEntry);
  }

  @GetMapping("/leaderboardEntry/{leaderboardId}")
  public List<LeaderboardEntry> listAllLeaderboardEntriesOfLeaderboard(@PathVariable int leaderboardId) {
    return leaderboardEntryRepo.findAllByLeaderboardIdOrderByLeaderboardEntryStudentNameAsc(leaderboardId);
  }

  private LeaderboardEntry getLeaderboardEntryByIds(@PathVariable int leaderboardId, @PathVariable int studentId) {
    return (leaderboardEntryRepo.findByLeaderboardIdAndStudentId(leaderboardId, studentId));
  }

  private boolean existsInLeaderboardEntry(@PathVariable int leaderboardId, @PathVariable int studentId) {
    return (leaderboardEntryRepo.existsByLeaderboardIdAndStudentId(leaderboardId, studentId));
  }

  private LeaderboardEntry updateLeaderboardEntryScore(@RequestBody LeaderboardEntry leaderboardEntry) {
    LeaderboardEntry dbLeaderboardEntry = getLeaderboardEntryByIds(leaderboardEntry.getLeaderboardId(),
        leaderboardEntry.getStudentId());

    if (dbLeaderboardEntry.getLeaderboardEntryScore() < leaderboardEntry.getLeaderboardEntryScore()) {

      dbLeaderboardEntry.setLeaderboardEntryScore(leaderboardEntry.getLeaderboardEntryScore());

      LeaderboardEntry newLeaderboardEntry = leaderboardEntryRepo.save(dbLeaderboardEntry);
      return newLeaderboardEntry;
    } else {
      return dbLeaderboardEntry;
    }
  }

  @PostMapping("/leaderboardEntry")
  public LeaderboardEntry decideLeaderboardEntry(@RequestBody LeaderboardEntry leaderboardEntry) {
    if (existsInLeaderboardEntry(leaderboardEntry.getLeaderboardId(), leaderboardEntry.getStudentId())) {
      return updateLeaderboardEntryScore(leaderboardEntry);
    } else {
      return createNewLeaderboardEntry(leaderboardEntry);
    }
  }

}
