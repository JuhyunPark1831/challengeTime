package com.sideProject.challengeTime.global.util;

import com.sideProject.challengeTime.config.JWTConfig;
import com.sideProject.challengeTime.domain.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtTokenProvider {
    private final JWTConfig jwtConfig;
    private final UserRepository userRepository;

    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 2000;

    public String createAccessToken(Long userId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, String.valueOf(jwtConfig.SECRET_KEY))
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }
}
