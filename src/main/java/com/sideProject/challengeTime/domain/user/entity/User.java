package com.sideProject.challengeTime.domain.user.entity;

import com.sideProject.challengeTime.domain.challenge.entity.UserChallenge;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private int Id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String phone_number;

    @OneToMany(mappedBy = "user")
    private Set<UserChallenge> userChallenges = new HashSet<>();

}
