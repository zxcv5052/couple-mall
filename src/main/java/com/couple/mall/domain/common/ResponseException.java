package com.couple.mall.domain.common;

/**
 * 공통 에러 코드와 메시지를 담은 예외 계획된 에러에 사용
 */
public class ResponseException extends Exception {

    private static final long serialVersionUID = 1L;

    private ErrorCode code;

    public ResponseException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ResponseException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public ResponseException enhanceMessage(String message) {
        return new ResponseException(code, getMessage() + message, getCause());
    }
}