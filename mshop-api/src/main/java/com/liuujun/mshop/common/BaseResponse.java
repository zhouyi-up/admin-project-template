package com.liuujun.mshop.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseResponse<T>  extends BaseResult{

    private T data;

    public static <R> BaseResponse<R> result(int code, R data, String message){
        BaseResponse<R> br = new BaseResponse<>();
        br.setData(data);
        br.setCode(code);
        br.setMessage(message);
        return br;
    }

    public static <R> BaseResponse<R> success(R data){
        return BaseResponse.result(0, data, "success");
    }
    public static <R> BaseResponse<R> success(){
        return BaseResponse.result(0, null, "success");
    }


    public static <R> BaseResponse<R> error(String message){
        return BaseResponse.result(999, null, message);
    }

    public static <R> BaseResponse<R> error(int code, String message){
        return BaseResponse.result(code, null, message);
    }
}
