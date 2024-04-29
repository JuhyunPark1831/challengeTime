package com.sideProject.challengeTime.domain.challenge.repository;

import com.sideProject.challengeTime.domain.challenge.entity.UserChallenge;
import com.sideProject.challengeTime.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    @Query("SELECT uc.user FROM UserChallenge uc WHERE uc.challenge.Id = :challengeId")
    List<User> findAllUsersByChallengeId(Long challengeId);

    void deleteByChallengeId(Long challengeId);

    void deleteUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);

    @Query("SELECT uc FROM UserChallenge uc WHERE uc.user.Id = :userId AND uc.challenge.Id = :challengeId")
    Optional<UserChallenge> findByUserAndChallenge(Long userId, Long challengeId);
}
