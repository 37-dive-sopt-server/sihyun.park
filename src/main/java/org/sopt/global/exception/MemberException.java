package org.sopt.global.exception;

import org.sopt.global.constant.MemberErrorCode;

public class MemberException extends GlobalException {
    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}