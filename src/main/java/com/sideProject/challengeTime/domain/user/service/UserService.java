package com.sideProject.challengeTime.domain.user.service;


import com.sideProject.challengeTime.domain.user.dto.UserDto;
import com.sideProject.challengeTime.domain.user.entity.User;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(UserDto.signUpRequestDto signUpRequestDto) {
        userRepository.save(User.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .nickname(signUpRequestDto.getNickname())
                .phone_number(signUpRequestDto.getPhone_number())
                .build());
    }
}
