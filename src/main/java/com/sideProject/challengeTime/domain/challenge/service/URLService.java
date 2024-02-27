package com.sideProject.challengeTime.domain.challenge.service;

import com.sideProject.challengeTime.domain.challenge.repository.URLRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class URLService {
    private final URLRepository urlRepository;

    public String checkChallenge(Long ruleId, Long userId) {
        return urlRepository.findURLByRuleIdAndUserId(ruleId, userId);
    }
}
