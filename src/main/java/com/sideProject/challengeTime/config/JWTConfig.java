package com.sideProject.challengeTime.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {
    @Value("${jwt.secret-key")
    public String SECRET_KEY;
}
