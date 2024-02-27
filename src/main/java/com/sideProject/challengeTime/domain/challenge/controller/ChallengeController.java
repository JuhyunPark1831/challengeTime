package com.sideProject.challengeTime.domain.challenge.controller;


import com.sideProject.challengeTime.domain.challenge.dto.ChallengeDto;
import com.sideProject.challengeTime.domain.challenge.dto.UserChallengeDto;
import com.sideProject.challengeTime.domain.challenge.service.ChallengeService;
import com.sideProject.challengeTime.domain.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@AllArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
    private final MessageService messageService;

    @PostMapping("/create")
    public void createChallenge(@RequestBody ChallengeDto.ChallengeRequestDto challengeRequestDto) {
        challengeService.createChallenge(challengeRequestDto);
    }

    @GetMapping("/delete/{challengeId}")
    public void deleteChallenge(@PathVariable Long challengeId) {
        challengeService.deleteChallenge(challengeId);
    }

    @PostMapping("/start")
    public void startChallenge(@RequestBody UserChallengeDto.UserChallengeRequestDto userChallengeRequestDto) {
        challengeService.startChallenge(userChallengeRequestDto);
    }

    @PostMapping("/end")
    public void endChallenge(@RequestBody UserChallengeDto.UserChallengeRequestDto userChallengeRequestDto) {
        challengeService.endChallenge(userChallengeRequestDto);
    }
}
