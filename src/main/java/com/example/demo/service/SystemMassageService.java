package com.example.demo.service;

import com.example.demo.entity.msg.ErrorMsg;

import java.util.List;


public interface SystemMassageService {

    /**
     * 获取所有错误码枚举类
     * @return
     */
    List<ErrorMsg> getAllErrorEnums();
}
