package com.liuujun.mshop.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException{

    private int code;
    private String message;

    public BaseException(int code, String message){
        super(message);
        this.message = message;
        this.code = code;
    }

    public BaseException(int code, String message, Exception exception){
        super(message, exception);
        this.code = code;
        this.message = message;
    }

    public static BaseException exception(String message){
        return new BaseException(999,message);
    }

    public static BaseException exception(int code, String message){
        return new BaseException(code,message);
    }
}
