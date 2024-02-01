package com.stock.analysis.exception;

public class ArticleAppException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public ArticleAppException(ErrorCode errorCode) {
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
