package com.sideProject.challengeTime.domain.challenge.repository;

import com.sideProject.challengeTime.domain.challenge.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface URLRepository extends JpaRepository<URL, Long> {

    @Query("SELECT u.URL FROM URL u WHERE u.rule.Id = :ruleId AND u.user.Id = :userId")
    String findURLByRuleIdAndUserId(Long ruleId, Long userId);
}
