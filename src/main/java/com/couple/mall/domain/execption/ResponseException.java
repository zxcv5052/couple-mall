package com.couple.mall.domain.execption;

import com.couple.mall.domain.api.common.Message;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 공통 에러 코드와 메시지를 담은 예외 계획된 에러에 사용
 */
@Getter
public class ResponseException extends Exception {

    private static final long serialVersionUID = 1L;

    private HttpStatus code;
    private Message<?> data;

    public ResponseException(Message<?> data, HttpStatus code, String message) {
        super(message);
        this.data = data;
        this.code = code;
    }

    public ResponseException(HttpStatus code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ResponseException enhanceMessage(String message) {
        return new ResponseException(code, getMessage() + message, getCause());
    }
}