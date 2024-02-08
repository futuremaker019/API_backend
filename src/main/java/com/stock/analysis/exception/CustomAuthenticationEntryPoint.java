package com.stock.analysis.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.analysis.dto.response.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 인증 실패시 동작 정의
     * 사용자의 인증이 실패했을 때 어떻게 처리할지를 정의한다.
     * ex) 사용자가 로그인 하지 않은 상테에서 보호되는 리소스에 접근하려고 할 때 인증을 요구하는데
     * authenticationEntryPoint 는 사용자를 로그인 페이지로 리디렉션하거나,
     * 인증 실패에 대한 특정 응답을 생성하는 등의 동작을 정의한다.
     */

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(ErrorCode.INVALID_TOKEN.getStatus().value());
        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, Response.error(ErrorCode.INVALID_TOKEN.name()));
            os.flush();
        }
    }
}
