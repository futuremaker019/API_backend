package com.stock.analysis.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils implements InitializingBean {

    private final CookieUtils cookieUtils;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private long accessTokenExpiredTimeMs;

    private static final String AUTHORITIES_KEY = "auth";
    private long refreshTokenExpiredTimeMs = accessTokenExpiredTimeMs + 60000;
    private Key key;

    /**
     * Access 토큰은 헤더에 담아보내고
     * Refresh 토큰을 쿠키에 담아서 발행하지만 갱신은 하지 않는것으로 하자
     */

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("secretKey = " + secretKey);
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // createToken
    public String createToken(Authentication auth, TokenType tokenType) {

        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        Map<String, Object> payload = new HashMap<>();
        payload.put(AUTHORITIES_KEY, authorities);
        payload.put("username", auth.getName());

        System.out.println("auth.getName() = " + auth.getName());

        long now = new Date().getTime();
        Date validTime = tokenType == TokenType.ACCESS_TOKEN ? new Date(now + accessTokenExpiredTimeMs) : new Date(now + refreshTokenExpiredTimeMs);

        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validTime)
                .signWith(this.key, SignatureAlgorithm.HS256)
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

    }

    // getAuthentication
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.get("username", String.class), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public void addRefreshTokenInCookie(String refreshToken, HttpServletResponse response) {
        Cookie refreshTokenCookie = cookieUtils.createCookie(TokenType.REFRESH_TOKEN.getValue(), refreshToken);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge((int)(refreshTokenExpiredTimeMs));
        response.addCookie(refreshTokenCookie);
    }

    public Claims extractAllClaims(String token, Key key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token, Key key) {
        return extractAllClaims(token, key).get("username", String.class);
    }

    public Boolean validate(String token, String username, Key key) {
        String usernameByToken = getUsername(token, key);
        return usernameByToken.equals(username) && !isTokenExpired(token, key);
    }

    private boolean isTokenExpired(String token, Key key) {
        Date expiration = extractAllClaims(token, key).getExpiration();
        return expiration.before(new Date());
    }
}
