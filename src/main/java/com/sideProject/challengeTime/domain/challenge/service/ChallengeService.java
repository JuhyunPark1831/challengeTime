package com.sideProject.challengeTime.domain.challenge.service;

import com.sideProject.challengeTime.domain.challenge.dto.ChallengeDto;
import com.sideProject.challengeTime.domain.challenge.dto.UserChallengeDto;
import com.sideProject.challengeTime.domain.challenge.entity.Challenge;
import com.sideProject.challengeTime.domain.challenge.entity.Rule;
import com.sideProject.challengeTime.domain.challenge.entity.UserChallenge;
import com.sideProject.challengeTime.domain.challenge.repository.ChallengeRepository;
import com.sideProject.challengeTime.domain.challenge.repository.RuleRepository;
import com.sideProject.challengeTime.domain.challenge.repository.UserChallengeRepository;
import com.sideProject.challengeTime.domain.user.entity.User;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final RuleRepository rulerepository;
    private final UserChallengeRepository userChallengeRepository;

    public void createChallenge(ChallengeDto.ChallengeRequestDto challengeRequestDto) {
        User creator = userRepository.findById(challengeRequestDto.getCreatorId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + challengeRequestDto.getCreatorId()));

        Challenge challenge = Challenge.builder()
                .name(challengeRequestDto.getName())
                .creator(creator)
                .build();

        List<Rule> rules = challengeRequestDto.getRules().stream()
                .map(ruleDto -> Rule.builder()
                        .title(ruleDto.getTitle())
                        .penalty(ruleDto.getPenalty())
                        .challengeTime(ruleDto.getChallengeTime())
                        .challengeComment(ruleDto.getChallengeComment())
                        .challenge(challenge)
                        .build())
                .collect(Collectors.toList());

        UserChallenge userChallenge = UserChallenge.builder()
                .challenge(challenge)
                .user(creator)
                .build();

        challengeRepository.save(challenge);
        userChallengeRepository.save(userChallenge);
        rulerepository.saveAll(rules);
    }

    public void deleteChallenge(Long challengeId) {
        userChallengeRepository.deleteByChallengeId(challengeId);
        rulerepository.deleteByChallengeId(challengeId);
        challengeRepository.deleteById(challengeId);
    }

    public void startChallenge(UserChallengeDto.UserChallengeRequestDto userChallengeRequestDto) {
        UserChallenge userChallenge = UserChallenge.builder()
                .user(userRepository.findById(userChallengeRequestDto.getUserId()).get())
                .challenge(challengeRepository.findById(userChallengeRequestDto.getChallengeId()).get())
                .build();
        userChallengeRepository.save(userChallenge);
    }

    public void endChallenge(UserChallengeDto.UserChallengeRequestDto userChallengeRequestDto) {
        userChallengeRepository.deleteUserChallengeByUserIdAndChallengeId(userChallengeRequestDto.getUserId(), userChallengeRequestDto.getChallengeId());
    }
}
