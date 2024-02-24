package com.sideProject.challengeTime.domain.message;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.repository.RuleRepository;
import com.sideProject.challengeTime.domain.challenge.repository.UserChallengeRepository;
import com.sideProject.challengeTime.domain.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.MultiMap;
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
    private static ConcurrentLinkedQueue<HashMap<Long, LocalDateTime>> challengeTimes = new ConcurrentLinkedQueue<>();

    @Scheduled(fixedDelay = 10000)
    public void loadChallengeTime() {
        List<Object[]> data = repository.findAllChallengeIdAndChallengeTimeOrderByChallengeTimeAsc();
        challengeTimes = data.stream()
                .map(row -> {
                    HashMap<Long, LocalDateTime> map = new HashMap<>();
                    map.put((Long) row[0], (LocalDateTime) row[1]);
                    return map;
                })
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));

    }
    @Scheduled(fixedDelay = 1000)
    public void checkChallengeTime() {
        if (!challengeTimes.isEmpty()) {
            HashMap<Long, LocalDateTime> firstChallenge = challengeTimes.peek(); // 큐에서 요소를 삭제하지 않고 가져옴
            Long challengeId = firstChallenge.keySet().iterator().next();
            LocalDateTime challengeTime = firstChallenge.get(challengeId);
            System.out.println("Challenge ID: " + challengeId + ", Challenge Time: " + challengeTime);
            LocalDateTime currentTime = LocalDateTime.now().withSecond(0).withNano(0); // 초와 나노초 제거
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // 원하는 포맷 설정
            String formattedTime = currentTime.format(formatter); // 포맷에 맞게 문자열 생성
            System.out.println("Formatted time: " + formattedTime);
            if (challengeTime.toString().equals(formattedTime)) { // 현재 시간과 도전과제 시간을 비교
                sendSMS(challengeId);
                challengeTimes.poll(); // 큐에서 해당 요소를 삭제
            }
        } else {
            System.out.println("Queue is empty.");
        } 
    }

    private void sendSMS(Long challengeId) {
        List<String> userPhoneNumbers = userChallengeRepository.findAllUserPhoneNumberByChallengeId(challengeId);
        String text = "챌린지 인증 시간입니다! Challenge Id: " + challengeId;

        userPhoneNumbers
                .forEach(phoneNumber -> messageService.sendSMS(phoneNumber, text));
    }
}
