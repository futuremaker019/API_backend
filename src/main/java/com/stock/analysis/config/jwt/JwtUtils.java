package com.stock.analysis.config.jwt;

import com.stock.analysis.application.useraccount.repository.UserAccountRepository;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.security.CustomUser;
import com.stock.analysis.exception.AuthenticationException;
import com.stock.analysis.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtils implements InitializingBean {

    private final CookieUtils cookieUtils;
    private Key key;
    private final UserAccountRepository userAccountRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;
    private final long accessTokenExpiredTimeMs;
    private final long refreshTokenExpiredTimeMs;

    public JwtUtils(
            @Value("${jwt.token.expired-time-ms}") long expiredTimeMs,
            CookieUtils cookieUtils,
            UserAccountRepository userAccountRepository
    ) {
        this.cookieUtils = cookieUtils;
        this.accessTokenExpiredTimeMs = expiredTimeMs;               // 1분
        this.refreshTokenExpiredTimeMs = expiredTimeMs * 60 * 24;    // 24시간
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Access 토큰은 헤더에 담아보내고
     * Refresh 토큰을 쿠키에 담아서 발행하지만 갱신은 하지 않는것으로 하자
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("secretKey = {}", secretKey);
        log.info("accessTokenExpiredTimeMs = {}", accessTokenExpiredTimeMs);
        log.info("refreshTokenExpiredTimeMs = {}", refreshTokenExpiredTimeMs);
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // createToken
    public String createToken(Authentication auth, TokenType tokenType) {

        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        Map<String, Object> payload = new HashMap<>();
        payload.put(HttpHeaders.AUTHORIZATION, authorities);
        payload.put("username", auth.getName());

        long now = new Date().getTime();
        Date validTime = tokenType == TokenType.ACCESS_TOKEN ? new Date(now + accessTokenExpiredTimeMs) : new Date(now + refreshTokenExpiredTimeMs);

        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validTime)
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
            throw e;
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.", e);
            throw new AuthenticationException(ErrorCode.REFRESH_EXPIRED_TOKEN, "Refresh token expired");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(HttpHeaders.AUTHORIZATION).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        String username = getUsername(token, this.key);
        UserAccount userAccount = userAccountRepository.findByUserId(username)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.USER_NOT_FOUND, "user not found - username : %s".formatted(username)));

//        User principal = new User(claims.get("username", String.class), "", authorities);
        return new UsernamePasswordAuthenticationToken(new CustomUser(userAccount), token, authorities);
    }

    public void addRefreshTokenInCookie(String refreshToken, HttpServletResponse response) {
        Cookie refreshTokenCookie = cookieUtils.createCookie(TokenType.REFRESH_TOKEN.getValue(), refreshToken, refreshTokenExpiredTimeMs);
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
