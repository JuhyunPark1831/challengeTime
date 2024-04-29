package com.sideProject.challengeTime.domain;

import com.sideProject.challengeTime.domain.user.entity.User;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/test")
    public String test() {
        return "asdfads";
    }
}
