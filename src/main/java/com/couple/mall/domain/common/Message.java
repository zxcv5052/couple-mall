package com.couple.mall.domain.common;

import lombok.Data;

@Data
public class Message {
    private boolean success;
    private StatusEnum status;
    private String message;
    private Object data;

    public void notFound(){
        this.status = StatusEnum.BAD_REQUEST;
        this.message = "잘못된 경로입니다.";
        this.success = false;
        this.data = null;
    }

    public void notAuth(){
        this.status = StatusEnum.NOT_AUTHORIZATION;
        this.message = "권한이 없습니다.";
        this.success = false;
        this.data = null;
    }

    public void badRequest(){
        this.status = StatusEnum.BAD_REQUEST;
        this.message = "입력 값이 매칭되지 않습니다.";
        this.success = false;
        this.data = null;
    }
    public void success(Object data){
        this.status = StatusEnum.OK;
        this.success = true;
        this.message = "성공입니다.";
        this.data = data;
    }
}