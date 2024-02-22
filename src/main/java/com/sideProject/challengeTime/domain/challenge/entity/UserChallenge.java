package com.sideProject.challengeTime.domain.challenge.entity;


import com.sideProject.challengeTime.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challengeMember_id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
