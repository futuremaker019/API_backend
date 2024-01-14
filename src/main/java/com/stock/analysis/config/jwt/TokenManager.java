package com.stock.analysis.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class TokenManager implements InitializingBean {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private long accessTokenExpiredTimeMs;

    private long refreshTokenExpiredTimeMs = accessTokenExpiredTimeMs + 60;

    private Key key;

    /**
     * Access 토큰은 헤더에 담아보내고
     * Refresh 토큰을 쿠키에 담아서 발행하지만 갱신은 하지 않는것으로 하자
     */

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private static Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // createToken

    public String createToken(Authentication auth, TokenType tokenType) {
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        Map payload = new HashMap<>();
        payload.put("auth", authorities);
        payload.put("username", auth.getName());
        long now = new Date().getTime();
        Date validTime = tokenType == TokenType.ACCESS_TOKEN ? new Date(now + accessTokenExpiredTimeMs) : new Date(now + refreshTokenExpiredTimeMs);

        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validTime)
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }


    // tokenValidation
    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw e;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw e;
        }
        // getAuthentication
    }

}
