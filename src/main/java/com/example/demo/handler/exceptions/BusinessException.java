package com.example.demo.handler.exceptions;


import com.example.demo.enums.EnumInterfase;


public class BusinessException extends RuntimeException{

    private EnumInterfase<?> errorCode;

    private String errorMsg;

    private BusinessException() {
    }

    /**
     * 业务异常类
     * @param errorCode
     */
    public BusinessException(EnumInterfase<?> errorCode) {
        super("业务异常");
        this.errorCode=errorCode;
    }

    public BusinessException(EnumInterfase<?> errorCode,String errorMsg) {
        super("业务异常");
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }

    public EnumInterfase<?> getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
