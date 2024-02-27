package com.sideProject.challengeTime.domain.message;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.entity.URL;
import com.sideProject.challengeTime.domain.challenge.repository.RuleRepository;
import com.sideProject.challengeTime.domain.challenge.repository.UserChallengeRepository;
import com.sideProject.challengeTime.domain.message.service.MessageService;
import com.sideProject.challengeTime.domain.user.entity.User;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChallengeTimeScheduler {
    private final MessageService messageService;
    private final RuleRepository repository;
    private final UserChallengeRepository userChallengeRepository;
    private final UserRepository userRepository;
    private static ConcurrentLinkedQueue<HashMap<Long, LocalDateTime>> challengeTimes = new ConcurrentLinkedQueue<>();
//todo: Rule로 가져오도록 수정
    @Scheduled(fixedDelay = 10000000)
    public void loadChallengeTime() {
        List<Rule> data = repository.findAllRulesOrderByChallengeTimeAsc();
        challengeTimes = data.stream()
                .map(row -> {
                    HashMap<Long, LocalDateTime> map = new HashMap<>();
                    map.put(row.getId(), (LocalDateTime) row.getChallengeTime());
                    return map;
                })
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));

    }
    @Scheduled(fixedDelay = 1000)
    public void checkChallengeTime() {
        if (challengeTimes.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        HashMap<Long, LocalDateTime> firstChallenge = challengeTimes.peek(); // 큐에서 요소를 삭제하지 않고 가져옴

        Long ruleId = firstChallenge.keySet().iterator().next();

        LocalDateTime challengeTime = firstChallenge.get(ruleId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // 원하는 포맷 설정
        String currentTime = LocalDateTime.now().withSecond(0).withNano(0).format(formatter); // 포맷에 맞게 문자열 생성

        System.out.println("Current Time: " + currentTime);
        System.out.println("Challenge Time: " + challengeTime);

        if (challengeTime.toString().equals(currentTime)) {
            sendEachSMS(repository.findById(ruleId).get());
            challengeTimes.poll(); // 큐에서 해당 요소를 삭제
        }
    }

    private void sendEachSMS(Rule rule) {
        List<User> users = userChallengeRepository.findAllUsersByChallengeId(rule.getChallenge().getId());
        users.forEach(user -> sendSMS(rule, user));
    }

    private void sendSMS(Rule rule, User user) {
        messageService.sendSMS(user.getPhone_number(), createMessage(rule, user));
    }

    private String createMessage(Rule rule, User user) {
        return user.getNickname() + "님! " + rule.getTitle() + "인증 시간입니다!\n"
                + "아래 URL로 접근해서 인증해주세요!\n" +
                "[" + createURL(rule, user);
    }

    private String createURL(Rule rule, User user) {
        String url = "/check/" + rule.getId() + "/" + user.getId();
        URL.builder()
                .valTime(LocalDateTime.now().plusMinutes(30))
                .URL(url)
                .build();
        return url;
    }
}
