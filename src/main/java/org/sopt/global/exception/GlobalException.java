package org.sopt.global.exception;

import org.sopt.global.constant.ErrorCode;

public class GlobalException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
