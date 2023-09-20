package com.example.demo.handler.exceptions;


import com.example.demo.enums.EnumInterfase;


public class CheckException extends RuntimeException{

    private EnumInterfase<?> errorCode;

    private CheckException() {
    }

    /**
     * 校验异常类
     * @param errorCode
     */
    public CheckException(EnumInterfase<?> errorCode) {
        super("校验异常");
        this.errorCode=errorCode;
    }

    public EnumInterfase<?> getErrorCode() {
        return errorCode;
    }
}
