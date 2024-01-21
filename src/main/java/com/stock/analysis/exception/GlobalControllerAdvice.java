package com.stock.analysis.exception;

import com.stock.analysis.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(AuthArgsException.class)
    public ResponseEntity<?> authArgsErrorHandler(AuthArgsException e) {
        log.error("Error occurred {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name(), e.getMessage()));
    }

    @ExceptionHandler(ApplyAppException.class)
    public ResponseEntity<?> errorHandler(ApplyAppException e) {
        log.error("Error occurred {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }

}
