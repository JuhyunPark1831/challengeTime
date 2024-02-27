package com.sideProject.challengeTime.domain.user.dto;

import lombok.Getter;

public class UserDto {
    @Getter
    public class signUpRequestDto {
        private String email;
        private String password;
        private String nickname;
        private String phone_number;
    }
}
