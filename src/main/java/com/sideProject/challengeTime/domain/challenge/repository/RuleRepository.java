package com.sideProject.challengeTime.domain.challenge.repository;

import com.sideProject.challengeTime.domain.challenge.entity.Challenge;
import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    @Query("SELECT r.Id FROM Rule r WHERE r.challenge.Id = :challengeId")
    List<Long> findAllIdsByChallengeId(Long challengeId);

    @Query("SELECT r FROM Rule r WHERE r.week = :week ORDER BY r.challengeTime ASC")
    List<Rule> findAllRulesOrderByChallengeTimeAsc(DayOfWeek week);

    void deleteByChallengeId(Long challegeId);

    List<Rule> findAllByChallenge(Challenge challenge);
}
