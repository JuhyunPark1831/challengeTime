package com.sideProject.challengeTime.domain.message.controller;


import com.sideProject.challengeTime.domain.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/asdf/qwer")
    public void asdf() {
        messageService.sendSMS();
    }
}
