package com.sideProject.challengeTime.domain.challenge.dto;

import com.sideProject.challengeTime.domain.challenge.entity.Challenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ChallengeDto {
    @Getter
    public static class ChallengeRequestDto {
        private String name;
        List<RuleDto> rules;
    }

    @Builder
    @Getter
    public static class ChallengeResponseDto {
        private Long id;
        private String name;
        private String nickname;
        private int memberNum;
        public static ChallengeResponseDto of(Challenge challenge, int memberNum) {
            return ChallengeResponseDto.builder()
                    .id(challenge.getId())
                    .name(challenge.getName())
                    .nickname(challenge.getCreator().getNickname())
                    .memberNum(memberNum)
                    .build();

        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class detailChallengeResponseDto {
        private String name;
        private int memberNum;
        private List<RuleDto> rules;
        private int userPenalty;
        //todo: 참여 상태 필드 추가?
    }
}
