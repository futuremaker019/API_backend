package com.stock.analysis.application.useraccount.service;

import com.stock.analysis.application.useraccount.repository.UserAccountRepository;
import com.stock.analysis.config.jwt.CookieUtils;
import com.stock.analysis.config.jwt.JwtUtils;
import com.stock.analysis.config.jwt.TokenType;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.UserAccountDto;
import com.stock.analysis.dto.request.UserJoinRequest;
import com.stock.analysis.dto.request.UserLoginRequest;
import com.stock.analysis.dto.response.UserLoginResponse;
import com.stock.analysis.exception.AuthenticationException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {

    private final JwtUtils jwtUtils;
    private final CookieUtils cookieUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String userId) {
        return userAccountRepository.findByUserId(userId).map(UserAccountDto::from);
    }

    public void saveUserAccount(UserJoinRequest userJoinRequest) {
        userAccountRepository.findByUserId(userJoinRequest.userId()).ifPresent(e -> {
            throw new AuthenticationException(ErrorCode.USER_EXISTED, "UserId Existed");
        });

        String encodedPassword = passwordEncoder.encode(userJoinRequest.password());
        userAccountRepository.save(userJoinRequest.toDto(encodedPassword).toEntity());
    }

    /**
     * token과 user의 정보를 같이 전달
     */
    @Transactional(readOnly = true)
    public UserLoginResponse loginUserAccount(UserLoginRequest request, HttpServletResponse response) {
        UserAccount userAccount = userAccountRepository.findByUserId(request.userId())
                .orElseThrow(() -> new AuthenticationException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), userAccount.getUserPassword())) {
            throw new AuthenticationException(ErrorCode.PASSWORD_NOT_MATCHED, ErrorCode.PASSWORD_NOT_MATCHED.getMessage());
        }
        String accessToken = getAccessToken(request, response);
        return UserLoginResponse.from(userAccount, accessToken);
    }

    private String getAccessToken(UserLoginRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.userId(), request.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication, TokenType.ACCESS_TOKEN);
        String refreshToken = jwtUtils.createToken(authentication, TokenType.REFRESH_TOKEN);
        jwtUtils.addRefreshTokenInCookie(refreshToken, response);

        return accessToken;
    }

    // refresh token을 검증하여 cookie에 넣어주고 accesstoken을 response 하는 작업이 필요하다.
    public String reissueToken(HttpServletRequest request) {
        String refreshToken = cookieUtils.getCookie(request, TokenType.REFRESH_TOKEN.getValue()).getValue();
        // refresh token이 없거나, 발급된 refresh token이 유효하지 않다면 에러를 던진다.
        if (StringUtils.isBlank(refreshToken) && !jwtUtils.validateToken(refreshToken)) {
            throw new AuthenticationException(ErrorCode.INVALID_REFRESH_TOKEN, ErrorCode.INVALID_REFRESH_TOKEN.getMessage());
//            return null;
        }
        // refresh token의 만료기간 이전이라면 access token을 재발급하여 돌려준다.
        // refresh token은 재발급하지 않는다.
        Authentication authentication = jwtUtils.getAuthentication(refreshToken);
        return jwtUtils.createToken(authentication, TokenType.ACCESS_TOKEN);
    }

    public void signout(HttpServletRequest request) {
        // TODO: security 로그아웃 처리 및 header에서 accessToken 삭제해야 한다.
        // 쿠키에 담겨있는 refresh token의 만료일을 지워준다.
        cookieUtils.deleteCookie(request, TokenType.REFRESH_TOKEN.name());
    }
}
