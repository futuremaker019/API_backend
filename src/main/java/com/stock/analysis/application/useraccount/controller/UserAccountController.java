package com.stock.analysis.application.useraccount.controller;

import com.stock.analysis.application.useraccount.service.UserAccountService;
import com.stock.analysis.dto.request.UserJoinRequest;
import com.stock.analysis.dto.request.UserLoginRequest;
import com.stock.analysis.dto.response.Response;
import com.stock.analysis.dto.response.UserLoginResponse;
import com.stock.analysis.exception.AuthenticationException;
import com.stock.analysis.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    /**
     * 가입성공 시, "SUCCESS" 메시지 전송
     */
    @PostMapping("/join")
    public Response<Void> join(@Valid @RequestBody UserJoinRequest request, Errors errors) {
        if (errors.hasErrors()) {
            String defaultMessage = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            System.out.println("errors = " + defaultMessage);
            throw new AuthenticationException(ErrorCode.HAS_NO_ARGUMENTS, defaultMessage);
        }

        userAccountService.saveUserAccount(request);
        return Response.success();
    }

    /**
     * 로그인 성공시, "SUCCESS" 메시지 및 바디 전송
     */
    @PostMapping("/login")
    public Response<UserLoginResponse> login(
            @Valid @RequestBody UserLoginRequest request,
            Errors errors,
            HttpServletResponse response
    ) {
        if (errors.hasErrors()) {
            String defaultMessage = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            System.out.println("errors = " + defaultMessage);
            throw new AuthenticationException(ErrorCode.HAS_NO_ARGUMENTS, defaultMessage);
        }

        return Response.success(userAccountService.loginUserAccount(request, response));
    }

    @GetMapping("/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request) {
        String accessToken = userAccountService.reissueToken(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, accessToken).body(accessToken);
    }
}

