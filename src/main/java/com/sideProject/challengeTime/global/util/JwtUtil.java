package com.sideProject.challengeTime.global.util;


import com.sideProject.challengeTime.config.JWTConfig;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class JwtUtil {
    private final JWTConfig jwtConfig;

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.SECRET_KEY).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.get("userId").toString());
    }

    public Boolean validateToken(String token) throws Exception {
        try {
            log.info("SECRET KEY :"+ jwtConfig.SECRET_KEY);

            Jwts.parser().setSigningKey(jwtConfig.SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Sign 오류", e);
            throw new Exception("Sign 오류: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("잘못된 토큰", e);
            throw new Exception("잘못된 토큰: " + e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("토큰 형식 오류", e);
            throw new Exception("토큰 형식 오류: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("토큰 유효기간 만료", e);
            throw new Exception("토큰 유효기간 만료: " + e.getMessage());
        } catch (NullPointerException e) {
            log.error("토큰 없음", e);
            throw new Exception("토큰 없음: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 토큰", e);
            throw new Exception("지원되지 않는 토큰: " + e.getMessage());
        }
    }
}
