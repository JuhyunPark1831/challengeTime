package com.sideProject.challengeTime.domain.challenge.dto;

import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
public class RuleDto {
    private String title;
    private int penalty;
    private DayOfWeek week;
    private LocalTime challengeTime;
    private String challengeComment;

    public static RuleDto of(Rule rule) {
        return RuleDto.builder()
                .title(rule.getTitle())
                .penalty(rule.getPenalty())
                .week(rule.getWeek())
                .challengeTime(rule.getChallengeTime())
                .challengeComment(rule.getChallengeComment())
                .build();
    }
}