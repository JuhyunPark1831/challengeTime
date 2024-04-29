package com.sideProject.challengeTime.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Builder
@Getter
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
    private DayOfWeek week;

    @Column
    private LocalTime challengeTime;

    @Column
    private String challengeComment;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
