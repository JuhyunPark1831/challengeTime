package com.sideProject.challengeTime.domain.challenge.dto;

import lombok.Getter;

import java.util.List;


public class ChallengeDto {
    @Getter
    public static class ChallengeRequestDto {
        private String name;
        private Long creatorId;
        List<RuleDto> rules;
    }
}
