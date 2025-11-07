package org.sopt.global.constant;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

public enum ArticleErrorCode implements ErrorCode {
    DUPLICATE_TITLE(CONFLICT, "이미 등록된 제목입니다."),
    NOT_FOUND_ARTICLE(NOT_FOUND, "해당 ID의 게시글을 찾을 수 없습니다."),
    INVALID_TAG(BAD_REQUEST, "유효하지 않은 태그 값입니다.");

    private final HttpStatus status;
    private final String message;

    ArticleErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() { return status; }

    @Override
    public String getMessage() { return message; }
}
