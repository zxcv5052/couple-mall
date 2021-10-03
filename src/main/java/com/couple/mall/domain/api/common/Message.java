package com.couple.mall.domain.api.common;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Message<D> {
    private boolean success;
    private D data;

    public static <D> Message<D> success(){
        Message<D> message = new Message<>();
        message.setSuccess(true);
        return message;
    }

    public static <D> Message<D> success(D object){
        Message<D> message = new Message<>();
        message.setSuccess(true);
        message.setData(object);
        return message;
    }
    public static <D> Message<D> fail(){
        Message<D> message = new Message<>();
        message.setSuccess(false);
        message.setData(null);
        return message;
    }
}
