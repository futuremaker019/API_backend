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
import com.stock.analysis.dto.security.CustomUser;
import com.stock.analysis.exception.AuthenticationException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {

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
    public UserLoginResponse loginUserAccount(UserLoginRequest userLoginRequest, HttpServletResponse response) {
        CustomUser customUser = (CustomUser) loadUserByUsername(userLoginRequest.userId());
        System.out.println("customUser = " + customUser);
        System.out.println("request = " + userLoginRequest);
        if (!passwordEncoder.matches(userLoginRequest.password(), customUser.getPassword())) {
            throw new AuthenticationException(ErrorCode.PASSWORD_NOT_MATCHED, ErrorCode.PASSWORD_NOT_MATCHED.getMessage());
        }
        String accessToken = getAccessToken(customUser, userLoginRequest, response);
        return UserLoginResponse.from(customUser.getUserAccount(), accessToken);
    }

    private String getAccessToken(CustomUser customUser, UserLoginRequest loginRequest, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(customUser, loginRequest.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication, TokenType.ACCESS_TOKEN);
        String refreshToken = jwtUtils.createToken(authentication, TokenType.REFRESH_TOKEN);
        jwtUtils.addRefreshTokenInCookie(refreshToken, response);

        return accessToken;
    }

    // refresh token을 검증하여 cookie에 넣어주고 accesstoken을 response 하는 작업이 필요하다.
    public String reissueToken(HttpServletRequest request) {
        Cookie cookie = cookieUtils.getCookie(request, TokenType.REFRESH_TOKEN.getValue());
        String refreshToken = null;
        if (cookie != null) {
            refreshToken = cookie.getValue();
            System.out.println("refreshToken = " + refreshToken);
        }
        // refresh token이 없거나, 발급된 refresh token이 유효하지 않다면 에러를 던진다.
        if (StringUtils.isBlank(refreshToken) || !jwtUtils.validateToken(refreshToken)) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND, ErrorCode.REFRESH_TOKEN_NOT_FOUND.getMessage());
//            return null;
        }
        // refresh token의 만료기간 이전이라면 access token을 재발급하여 돌려준다.
        Authentication authentication = jwtUtils.getAuthentication(refreshToken);
        return jwtUtils.createToken(authentication, TokenType.ACCESS_TOKEN);
    }

    public void signout(HttpServletRequest request, HttpServletResponse response) {
        // TODO: security 로그아웃 처리 및 header에서 accessToken 삭제해야 한다.
        // 쿠키에 담겨있는 refresh token의 만료일을 지워준다.
        Cookie cookie = cookieUtils.getCookie(request, TokenType.REFRESH_TOKEN.getValue());
        if (cookie != null){
            cookieUtils.deleteCookie(response, cookie);
//            response.addCookie(cookieUtils.deleteCookie(request, TokenType.REFRESH_TOKEN.getValue()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUserId(username)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.USER_NOT_FOUND, "user not found - username : %s".formatted(username)));
        return new CustomUser(userAccount);
    }
}
