package com.sideProject.challengeTime.domain.message.service;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MessageService {
    @Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String apiSecret;

    @Value("${coolsms.fromnumber}")
    private String fromNumber;

    public void sendSMS() {
        System.out.println(apiKey);
        System.out.println(apiSecret);
        System.out.println(fromNumber);
        Message coolsms = new Message(apiKey, apiSecret);
        HashMap<String, String> set = new HashMap<>();
        set.put("to", "01071967401");

        set.put("from", fromNumber);
        set.put("text", "제발 되라..");
        set.put("type", "sms");
        set.put("app_version", "test app 1.2");

        try {
            JSONObject object = (JSONObject) coolsms.send(set);
            System.out.println(object.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
