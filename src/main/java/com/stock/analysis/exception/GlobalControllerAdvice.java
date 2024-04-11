package com.stock.analysis.exception;

import com.stock.analysis.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authArgsErrorHandler(AuthenticationException e) {
        log.error("Error occurred {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }

    @ExceptionHandler(ApplyAppException.class)
    public ResponseEntity<?> applyErrorHandler(ApplyAppException e) {
        log.error("Error occurred {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }

    @ExceptionHandler(CodeAppException.class)
    public ResponseEntity<?> codeErrorHandler(CodeAppException e) {
        log.error("Error occurred {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }

    /**
     * 파일업로드 관련 : 파일 사이즈 초과 exception
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxUploadSizeExceededErrorHandler(MaxUploadSizeExceededException e) {
        log.error("Error occurred {}", e.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error("%s, max file size of each - %s".formatted(ErrorCode.MAX_UPLOAD_SIZE_EXCEEDED.getMessage(), maxFileSize)));
    }

}
