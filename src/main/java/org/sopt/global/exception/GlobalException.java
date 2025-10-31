package org.sopt.global.exception;

import org.sopt.global.constant.MemberErrorCode;

public class GlobalException extends RuntimeException {
    private final MemberErrorCode errorCode;
    private final String message;

    public GlobalException(MemberErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public MemberErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
