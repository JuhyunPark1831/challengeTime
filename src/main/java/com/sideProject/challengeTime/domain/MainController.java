package com.sideProject.challengeTime.domain;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/api/hello")
    public String mainPage() {
        return "main 페이지";
    }
}
