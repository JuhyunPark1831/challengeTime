package com.sideProject.challengeTime.domain.user.dto;

import lombok.Getter;

public class UserDto {

    @Getter
    public static class loginRequestDto {
        private String email;
        private String password;
    }
    @Getter
    public static class signUpRequestDto {
        private String email;
        private String password;
        private String nickname;
        private String phone_number;
    }
}
//todo: jwt 회원가입 후 로그인 테스트
