package com.sideProject.challengeTime.domain.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


public class ChallengeDto {
    @Getter
    public static class ChallengeRequestDto {
        private String name;
        private Long creatorId;
        List<RuleDto> rules;
    }

    public static class ChallengeResponseDto {

    }
}
