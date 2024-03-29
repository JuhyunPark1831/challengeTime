package com.sideProject.challengeTime.domain.user.service;


import com.sideProject.challengeTime.domain.user.dto.UserDto;
import com.sideProject.challengeTime.domain.user.entity.User;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import com.sideProject.challengeTime.global.util.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(UserDto.signUpRequestDto signUpRequestDto) {
        userRepository.save(User.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .nickname(signUpRequestDto.getNickname())
                .phone_number(signUpRequestDto.getPhone_number())
                .build());
    }

    public String login(UserDto.loginRequestDto loginRequestDto) throws Exception {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new Exception("틀린 정보 입력"));

        return jwtTokenProvider.createAccessToken(user.getId());
    }
}
