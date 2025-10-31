package org.sopt.global.response;

import org.sopt.global.constant.MemberSuccessCode;
import org.springframework.http.HttpStatus;

public class GlobalBaseResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    private GlobalBaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> GlobalBaseResponse<T> ok(T data) {
        return new GlobalBaseResponse<>(HttpStatus.OK.value(), "요청이 성공적으로 처리되었습니다.", data);
    }

    public static <T> GlobalBaseResponse<T> error(HttpStatus status, String message) {
        return new GlobalBaseResponse<>(status.value(), message, null);
    }

    public static <T> GlobalBaseResponse<T> ok(MemberSuccessCode successCode) {
        return new GlobalBaseResponse<>(
                successCode.getStatus().value(),
                successCode.getMessage(),
                null
        );
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
