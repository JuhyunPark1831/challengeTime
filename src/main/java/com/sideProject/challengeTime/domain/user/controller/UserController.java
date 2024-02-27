package com.sideProject.challengeTime.domain.user.controller;


import com.sideProject.challengeTime.domain.user.dto.UserDto;
import com.sideProject.challengeTime.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public void signUp(@RequestBody UserDto.signUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto);
    }
}
