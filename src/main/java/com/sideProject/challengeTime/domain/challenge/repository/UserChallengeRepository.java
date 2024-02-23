package com.sideProject.challengeTime.domain.challenge.repository;

import com.sideProject.challengeTime.domain.challenge.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    void deleteByChallengeId(Long challengeId);

    void deleteUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);
}
