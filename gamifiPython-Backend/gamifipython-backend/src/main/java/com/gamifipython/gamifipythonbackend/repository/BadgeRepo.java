package com.gamifipython.gamifipythonbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifipython.gamifipythonbackend.models.Badge;

@Repository
public interface BadgeRepo extends JpaRepository<Badge, Long> {

}
