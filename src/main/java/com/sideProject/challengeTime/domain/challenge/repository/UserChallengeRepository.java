package com.sideProject.challengeTime.domain.challenge.repository;

import com.sideProject.challengeTime.domain.challenge.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    @Query("SELECT uc.user.phone_number FROM UserChallenge uc")
    List<String> findAllUserPhoneNumberByChallengeId(Long challengeId);

    void deleteByChallengeId(Long challengeId);

    void deleteUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);
}
