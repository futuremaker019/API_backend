package com.stock.analysis.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Authentication
    HAS_NO_ARGUMENTS(HttpStatus.BAD_REQUEST, "has no Arguments"),           // 인풋의 데이터가 없을떄
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    USER_EXISTED(HttpStatus.CONFLICT, "User Existed"),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid refresh token"),

    HAS_NO_AUTHORITIES(HttpStatus.FORBIDDEN, "Has no Authorities"),

    PASSWORD_NOT_MATCHED(HttpStatus.NOT_FOUND, "Password not matched")

    ;


    private final HttpStatus status;
    private final String message;

}
