package com.couple.mall.domain.common;

import org.springframework.http.HttpStatus;

public enum StatusEnum {

    OK(HttpStatus.OK, "OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND"),
    NOT_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "NOT_AUTHORIZATION"),
    INTERNAL_SERER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");

    public HttpStatus statusCode;
    public String code;

    StatusEnum(HttpStatus statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}