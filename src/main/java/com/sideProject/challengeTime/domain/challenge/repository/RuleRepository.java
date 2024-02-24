package com.sideProject.challengeTime.domain.challenge.repository;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    @Query("SELECT r.Id FROM Rule r WHERE r.challenge.Id = :challengeId")
    List<Long> findAllIdsByChallengeId(Long challengeId);

    @Query("SELECT r.challenge.Id, r.challengeTime from Rule r ORDER BY r.challengeTime ASC")
    List<Object[]> findAllChallengeIdAndChallengeTimeOrderByChallengeTimeAsc();

    void deleteByChallengeId(Long challegeId);
}
