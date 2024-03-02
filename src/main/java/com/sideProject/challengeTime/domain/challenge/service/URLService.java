package com.sideProject.challengeTime.domain.challenge.service;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.entity.URL;
import com.sideProject.challengeTime.domain.challenge.repository.URLRepository;
import com.sideProject.challengeTime.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class URLService {
    private final URLRepository urlRepository;

    @Value("${custom.url.prefix")
    private static String url_prefix;


    public String checkChallenge(Long ruleId, Long userId) {
        return urlRepository.findURLByRuleIdAndUserId(ruleId, userId);
    }

    public String createURL(Rule rule, User user) {
        String url =  url_prefix + "/check/" + rule.getId() + "/" + user.getId();
        urlRepository.save(URL.builder()
                .valTime(LocalDateTime.now().plusMinutes(30))
                .URL(url)
                .build());
        return url;
    }
}
