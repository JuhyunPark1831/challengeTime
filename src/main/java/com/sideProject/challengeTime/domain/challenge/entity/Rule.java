package com.sideProject.challengeTime.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String title;

    @Column
    private int penalty;

    @Column
    private LocalDateTime challengeTime;

    @Column
    private String challengeComment;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
