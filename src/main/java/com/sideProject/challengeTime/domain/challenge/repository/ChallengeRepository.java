package com.sideProject.challengeTime.domain.challenge.repository;

import com.sideProject.challengeTime.domain.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
