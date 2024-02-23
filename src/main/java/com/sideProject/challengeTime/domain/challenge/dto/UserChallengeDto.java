package com.sideProject.challengeTime.domain.challenge.dto;

import lombok.Getter;

import java.util.List;

public class UserChallengeDto {
    @Getter
    public static class UserChallengeRequestDto {
        private Long userId;
        private Long challengeId;
    }
}
