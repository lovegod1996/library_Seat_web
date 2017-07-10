package com.xoqao.web.exception;

/**
 * 自定义异常类
 * Author: lovegod
 * Created by 123 on 2016/11/26.
 */
public class CustomException extends Exception {
    //异常信息
    public  String message;
    public CustomException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
