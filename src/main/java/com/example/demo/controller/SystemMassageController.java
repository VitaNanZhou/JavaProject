package com.example.demo.controller;

import com.example.demo.entity.core.Response;
import com.example.demo.entity.msg.ErrorMsg;
import com.example.demo.service.SystemMassageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/system_error_msg")
@Api(tags = {"错误码 SystemMassageController"})
public class SystemMassageController {

@Autowired
private SystemMassageService systemMassageService;
    @GetMapping("/all")
    @ApiOperation(value = "获取所有错误码")
    public Response<List<ErrorMsg>> getMsgAll(){
        List<ErrorMsg> allErrorEnums = systemMassageService.getAllErrorEnums();
        return Response.success(allErrorEnums);
    }
}
