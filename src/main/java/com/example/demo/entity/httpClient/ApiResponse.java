package com.example.demo.entity.httpClient;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@ApiModel(value="ApiResponse 响应体")
public class ApiResponse<T> {
    private int code; // 响应状态码
    private String message; // 响应消息
    private T data; // 响应数据
    private Boolean errorResult; // 错误结果标志
}
