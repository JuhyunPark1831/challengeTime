package com.sideProject.challengeTime.domain.message.service;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.repository.UserChallengeRepository;
import com.sideProject.challengeTime.domain.challenge.service.URLService;
import com.sideProject.challengeTime.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final UserChallengeRepository userChallengeRepository;
    private final MessageAPIService messageAPIService;
    private final URLService urlService;

    public void sendEachSMS(Rule rule) {
        List<User> users = userChallengeRepository.findAllUsersByChallengeId(rule.getChallenge().getId());
        users.forEach(user -> sendSMS(rule, user));
    }

    private void sendSMS(Rule rule, User user) {
        messageAPIService.sendSMS(user.getPhone_number(), createMessage(rule, user));
    }

    private String createMessage(Rule rule, User user) {
        return user.getNickname() + "님! " + rule.getTitle() + "인증 시간입니다!\n"
                + "아래 URL로 접근해서 인증해주세요!\n" +
                "[" + urlService.createURL(rule, user);
    }
}
