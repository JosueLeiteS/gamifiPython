package com.gamifipython.gamifipythonbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifipython.gamifipythonbackend.models.Leaderboard;

@Repository
public interface LeaderboardRepo extends JpaRepository<Leaderboard, Long> {

}
