package com.sideProject.challengeTime.domain.challenge.controller;


import com.sideProject.challengeTime.domain.challenge.dto.ChallengeDto;
import com.sideProject.challengeTime.domain.challenge.dto.UserChallengeDto;
import com.sideProject.challengeTime.domain.challenge.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/challenge")
@AllArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    @PostMapping("/create")
    public void createChallenge(Principal principal, @RequestBody ChallengeDto.ChallengeRequestDto challengeRequestDto) {
        challengeService.createChallenge(Long.valueOf(principal.getName()), challengeRequestDto);
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

    @GetMapping("/list")
    public List<ChallengeDto.ChallengeResponseDto> findAllChallenge() {
        return challengeService.findAllChallenge();
    }

    @GetMapping("/{challenge_id}")
    public ChallengeDto.detailChallengeResponseDto findDetailChallnge(Principal principal, @PathVariable Long challenge_id) {
        return challengeService.findDetialChallenge(Long.valueOf(principal.getName()), challenge_id);
    }
}
