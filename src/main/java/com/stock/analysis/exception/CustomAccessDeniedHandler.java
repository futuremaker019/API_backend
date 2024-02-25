package com.stock.analysis.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.analysis.dto.response.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        /**
         *  AccessDeniedHandler는 사용자가 특정 리소스에 대한 권한이 없을 때 어떻게 처리할지를 정의한다.
         *  - 인증은 했으나 인가에 대한 권한이 없다는 의미
         *  ex) 사용자가 특정 권한이 없는 페이지에 접근을 시도할때, accessDeniedHandler는
         *      사용자에게 접근 거부에 대한 적절한 응답을 생성하거나, 특정 에러 페이지로 리디렉션하는 등의 동작을 정의한다.
         */

        response.setContentType("application/json");
        response.setStatus(ErrorCode.HAS_NO_AUTHORITIES.getStatus().value());
        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, Response.error(ErrorCode.HAS_NO_AUTHORITIES.name()));
            os.flush();
        }
    }
}
