package com.sideProject.challengeTime.domain.challenge.service;

import com.sideProject.challengeTime.domain.challenge.dto.ChallengeDto;
import com.sideProject.challengeTime.domain.challenge.dto.RuleDto;
import com.sideProject.challengeTime.domain.challenge.entity.Challenge;
import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.repository.ChallengeRepository;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    public void createChallenge(ChallengeDto.ChallengeRequestDto challengeRequestDto) {
        Challenge challenge = Challenge.builder()
                .creator(userRepository.findById(challengeRequestDto.getCreatorId()).get())
                .name(challengeRequestDto.getName())
                .rules(convertRuleDtoToEntity(challengeRequestDto.getRules())).build();
        challengeRepository.save(challenge);
    }

    private List<Rule> convertRuleDtoToEntity(List<RuleDto> ruleDtos) {
        return ruleDtos.stream()
                .map(this::converToEntity)
                .collect(Collectors.toList());
    }

    private Rule converToEntity(RuleDto ruleDto) {
        return Rule.builder()
                .challengeComment(ruleDto.getChallengeComment())
                .title(ruleDto.getTitle())
                .penalty(ruleDto.getPenalty())
                .challengeTime(ruleDto.getChallengeTime()).build();
    }
}
