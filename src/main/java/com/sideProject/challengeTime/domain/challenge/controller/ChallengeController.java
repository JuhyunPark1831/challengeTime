package com.sideProject.challengeTime.domain.challenge.controller;


import com.sideProject.challengeTime.domain.challenge.dto.ChallengeDto;
import com.sideProject.challengeTime.domain.challenge.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenge")
@AllArgsConstructor
public class ChallengeController {
    private ChallengeService challengeService;

    @PostMapping("/create")
    public String createChallenge(@RequestBody ChallengeDto.ChallengeRequestDto challengeRequestDto) {
        challengeService.createChallenge(challengeRequestDto);
        return "챌린지 생성";
    }

    @GetMapping("/delete/{challengeId}")
    public String deleteChallenge() {
        return "챌린지 삭제";
    }

    @PostMapping("/start")
    public String startChallenge() {
        return "챌린지 참여";
    }

    @PostMapping("/end")
    public String endChallenge() {
        return "챌린지 탈퇴";
    }
}
