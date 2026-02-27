package com.my.springboot.demo.utils;

import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 是状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public void setData(T data) {
        if (ObjectUtils.isEmpty(data)) {
            this.data = (T) "";
        } else {
            this.data = data;
        }
    }

    public Result() {

    }

    /**
     * 其他异常处理方法返回的结果
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> failed(T data) {
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        result.setData(data);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> failed(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        result.setSuccess(false);
        return result;
    }

    public static Result<String> businessFailed(BusinessException exception) {
        Result<String> result = new Result<>();
        result.setCode(exception.errorCode);
        result.setMsg(exception.errorMsg);
        result.setData("");
        result.setSuccess(false);
        return result;
    }

}
