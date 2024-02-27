package com.sideProject.challengeTime.domain.challenge.controller;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.entity.URL;
import com.sideProject.challengeTime.domain.challenge.repository.RuleRepository;
import com.sideProject.challengeTime.domain.challenge.repository.URLRepository;
import com.sideProject.challengeTime.domain.challenge.service.URLService;
import com.sideProject.challengeTime.domain.user.entity.User;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/check")
@AllArgsConstructor
public class URLController {

    private final RuleRepository repository;
    private final UserRepository userRepository;
    private final URLRepository urlRepository;

    private final URLService urlService;

    @GetMapping("/{ruleId}/{userId}")
    public ResponseEntity<?> checkChallenge(@PathVariable Long ruleId, @PathVariable Long userId) {
        String result = urlService.checkChallenge(ruleId, userId);

        Optional<String> optionalResult = Optional.ofNullable(result);
        return optionalResult.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("잘못된 URL"));
    }

    @GetMapping("/test/{ruleId}/{userId}")
    public String createURL(@PathVariable Long ruleId, @PathVariable Long userId) {
        String url = "/check/" + ruleId + "/" + userId;
        urlRepository.save(URL.builder()
                .valTime(LocalDateTime.now().plusMinutes(30))
                .URL(url)
                .rule(repository.findById(ruleId).get())
                .user(userRepository.findById(userId).get())
                .build());
        return "http://localhost:8080" + url;
    }
}
