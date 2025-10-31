package org.sopt.global.constant;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum MemberErrorCode {

    INVALID_NAME(BAD_REQUEST, "이름을 입력해주세요."),
    UNDER_AGE(BAD_REQUEST, "20세 미만은 가입이 불가합니다."),
    INVALID_EMAIL(BAD_REQUEST, "이메일을 입력해주세요."),
    INVALID_FORMAT_EMAIL(BAD_REQUEST, "유효하지 않은 이메일 형식입니다."),
    DUPLICATE_EMAIL(BAD_REQUEST, "이미 가입된 이메일입니다."),
    INVALID_GENDER(BAD_REQUEST, "성별을 입력해주세요."),
    NOT_FOUND_MEMBER(NOT_FOUND, "해당 ID의 회원이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    MemberErrorCode(HttpStatus status, String message) {
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
