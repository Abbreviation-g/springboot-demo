package com.my.springboot.demo.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

	protected Integer errorCode;
    protected String errorMsg;

    public BusinessException(){

    }
    public BusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
