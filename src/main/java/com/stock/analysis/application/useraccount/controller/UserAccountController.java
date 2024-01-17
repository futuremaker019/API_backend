package com.stock.analysis.application.useraccount.controller;

import com.stock.analysis.application.useraccount.service.UserAccountService;
import com.stock.analysis.dto.request.UserJoinRequest;
import com.stock.analysis.dto.request.UserLoginRequest;
import com.stock.analysis.dto.response.Response;
import com.stock.analysis.dto.response.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    /**
     * 가입성공 시, "SUCCESS" 메시지 전송
     */
    @PostMapping("/join")
    public Response<Void> join(@Valid @RequestBody UserJoinRequest request) {
        userAccountService.saveUserAccount(request.userId(), request.password());
        return Response.success();
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        return userAccountService.loginUserAccount(request);
    }

}

