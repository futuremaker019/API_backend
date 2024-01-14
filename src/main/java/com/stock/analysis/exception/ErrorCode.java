package com.stock.analysis.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    HAS_NO_AUTHORITIES(HttpStatus.UNAUTHORIZED, "Has no Authorities"),

    ;

    private final HttpStatus status;
    private final String message;

}
