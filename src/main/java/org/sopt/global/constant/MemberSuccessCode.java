package org.sopt.global.constant;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

public enum MemberSuccessCode {
    DELETED_MEMBER(OK, "회원이 성공적으로 삭제되었습니다.");

    private final HttpStatus status;
    private final String message;

    MemberSuccessCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
