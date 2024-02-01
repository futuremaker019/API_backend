package com.stock.analysis.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public AuthenticationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message != null) {
            return message;
        }
        return String.format("%s, %s", errorCode.getMessage(), message);
    }

}
