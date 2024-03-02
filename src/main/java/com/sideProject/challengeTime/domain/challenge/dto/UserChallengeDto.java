package com.sideProject.challengeTime.domain.challenge.dto;

import lombok.Getter;

public class UserChallengeDto {
    @Getter
    public static class UserChallengeRequestDto {
        private Long userId;
        private Long challengeId;
    }
}
