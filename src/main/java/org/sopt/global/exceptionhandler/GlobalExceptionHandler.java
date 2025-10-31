package org.sopt.global.exceptionhandler;

import org.sopt.global.exception.GlobalException;
import org.sopt.global.response.GlobalBaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

   //커스텀 예외
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<GlobalBaseResponse<Void>> handleGlobalException(GlobalException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(GlobalBaseResponse.error(
                        e.getErrorCode().getStatus(),
                        e.getErrorCode().getMessage()
                ));
    }

    //지원X 메서드 호출 예외
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GlobalBaseResponse<Void>> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity
                .status(org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED)
                .body(GlobalBaseResponse.error(
                        org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED,
                        "지원하지 않는 HTTP 메서드 " + e.getMethod()
                ));
    }

    //JSON 파싱 예외
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GlobalBaseResponse<Void>> handleJsonParseException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .badRequest()
                .body(GlobalBaseResponse.error(
                        org.springframework.http.HttpStatus.BAD_REQUEST,
                        "JSON 형식 오류"
                ));
    }

    //기타 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalBaseResponse<Void>> handleUnexpectedException(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(GlobalBaseResponse.error(
                        org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                        "서버 내부 오류" + e.getMessage()
                ));
    }
}
