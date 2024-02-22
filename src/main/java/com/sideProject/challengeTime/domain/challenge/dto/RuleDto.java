package com.sideProject.challengeTime.domain.challenge.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RuleDto {
    private String title;
    private int penalty;
    private LocalDateTime challengeTime;
    private String challengeComment;
}
