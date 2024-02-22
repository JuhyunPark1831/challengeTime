package com.sideProject.challengeTime.domain.challenge.entity;


import com.sideProject.challengeTime.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long Id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "challenge")
    private Set<UserChallenge> userChallenges = new HashSet<>();

    @OneToMany(mappedBy = "challenge")
    private Set<Rule> rules = new HashSet<>();


}
