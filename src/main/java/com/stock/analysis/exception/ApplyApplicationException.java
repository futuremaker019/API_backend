package com.stock.analysis.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.message.StringFormattedMessage;

@Getter
@AllArgsConstructor
public class ApplyApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public ApplyApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message != null) {
            return errorCode.getMessage();
        }
        return String.format("%s, %s", errorCode.getMessage(), message);
    }
}
