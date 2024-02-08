package com.stock.analysis.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 프론트에서 에러구분을 httpstatus와 enum명으로 구분하기 때문에 수정시 고려하자 - httpstatus와 enum명 중 하나만 고려하면 될거 같긴하다
     */
    
    //Authentication
    HAS_NO_ARGUMENTS(HttpStatus.BAD_REQUEST, "has no Arguments"),           // 인풋의 데이터가 없을떄
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    USER_EXISTED(HttpStatus.CONFLICT, "User Existed"),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid refresh token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "token expired"),

    HAS_NO_AUTHORITIES(HttpStatus.FORBIDDEN, "Has no Authorities"),

    PASSWORD_NOT_MATCHED(HttpStatus.NOT_FOUND, "Password not matched"),
    CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "Code not found"),

    ;

    private final HttpStatus status;
    private final String message;

}
