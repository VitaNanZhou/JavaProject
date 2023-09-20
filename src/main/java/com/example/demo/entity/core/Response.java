package com.example.demo.entity.core;

import com.example.demo.config.staticConfig.ResponseType;
import com.example.demo.enums.EnumInterfase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value="结果集")
public class Response<T> {

    @ApiModelProperty(value="数据集")
    private T data;
    @ApiModelProperty(value="状态")
    private String success;
    @ApiModelProperty(value="错误码")
    private EnumInterfase<?> errorCode;

    public static <T> Response<T> success(){
        Response<T> tResponse = new Response<>();
        tResponse.setSuccess(ResponseType.success);
        return tResponse;
    }

    public static <T> Response<T> success(T data){
        Response<T> tResponse = new Response<>();
        tResponse.setSuccess(ResponseType.success);
        tResponse.setData(data);
        return tResponse;
    }

    public static <T> Response<T> fail(EnumInterfase<?> errorCode){
        Response<T> tResponse = new Response<>();
        tResponse.setSuccess(ResponseType.fail);
        tResponse.setErrorCode(errorCode);
        return tResponse;
    }

    public static <T> Response<T> fail(EnumInterfase<?> errorCode,T data){
        Response<T> tResponse = new Response<>();
        tResponse.setSuccess(ResponseType.fail);
        tResponse.setErrorCode(errorCode);
        tResponse.setData(data);
        return tResponse;
    }
}
