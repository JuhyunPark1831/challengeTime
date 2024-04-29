package com.sideProject.challengeTime.domain.message;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.repository.RuleRepository;
import com.sideProject.challengeTime.domain.challenge.repository.URLRepository;
import com.sideProject.challengeTime.domain.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChallengeTimeScheduler {
    private final RuleRepository repository;
    private final MessageService messageService;
    private final URLRepository urlRepository;

    private static ConcurrentLinkedQueue<HashMap<Long, LocalTime>> challengeTimes = new ConcurrentLinkedQueue<>();

//    @Scheduled(fixedDelay = 1000)
//    public void checkURLValidTime() {
//        urlRepository.deleteByValTimeAfterNow();
//    }
    @Scheduled(fixedDelay = 10000000)
    public void loadChallengeTime() {
        List<Rule> data = repository.findAllRulesOrderByChallengeTimeAsc(LocalDate.now().getDayOfWeek());
        challengeTimes = data.stream()
                .map(row -> {
                    HashMap<Long, LocalTime> map = new HashMap<>();
                    map.put(row.getId(), row.getChallengeTime());
                    return map;
                })
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }

//    @Scheduled(fixedDelay = 1000)
//    public void checkChallengeTime() {
//        if (challengeTimes.isEmpty()) {
//            System.out.println("Queue is empty.");
//            return;
//        }
//        HashMap<Long, LocalDateTime> firstChallenge = challengeTimes.peek(); // 큐에서 요소를 삭제하지 않고 가져옴
//
//        Long ruleId = firstChallenge.keySet().iterator().next();
//
//        LocalDateTime challengeTime = firstChallenge.get(ruleId);
//        String currentTime = getCurrentTime();
//
//        System.out.println("Current Time: " + currentTime);
//        System.out.println("Challenge Time: " + challengeTime);
//
//        if (challengeTime.toString().equals(currentTime)) {
//            messageService.sendEachSMS(repository.findById(ruleId).get());
//            challengeTimes.poll(); // 큐에서 해당 요소를 삭제
//        }
//    }

    public String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // 원하는 포맷 설정
        return LocalDateTime.now().withSecond(0).withNano(0).format(formatter); // 포맷에 맞게 문자열 생성
    }
}
