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
    HAS_NO_ARGUMENTS(HttpStatus.BAD_REQUEST, "has no Arguments"),           // 400 인풋의 데이터가 없을떄
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    USER_EXISTED(HttpStatus.CONFLICT, "User Existed"),

    //Authentication
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),                // 401
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.GONE, "Invalid refresh token"),      // 410
    EXPIRED_TOKEN(HttpStatus.GONE, "token expired"),
    REFRESH_EXPIRED_TOKEN(HttpStatus.GONE, "Refresh token expired"),

    HAS_NO_AUTHORITIES(HttpStatus.FORBIDDEN, "Has no Authorities"),         // 403
    PASSWORD_NOT_MATCHED(HttpStatus.NOT_FOUND, "Password not matched"),

    CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "Code not found"),                 // 404
    CODE_NAME_EXISTED(HttpStatus.CONFLICT, "code name existed"),            // 409

    CODE_CHILDREN_EXISTED(HttpStatus.CONFLICT, "children existed"),          // 409

    MAX_UPLOAD_SIZE_EXCEEDED(HttpStatus.INTERNAL_SERVER_ERROR, "Max upload size exceeded")  // 500
    ,
    CONTENT_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "CONTENT_NOT_FOUND");

    private final HttpStatus status;
    private final String message;

}
