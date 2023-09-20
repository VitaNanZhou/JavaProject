package com.example.demo.controller;

import com.example.demo.api.HttpHelper;
import com.example.demo.api.Restful.AuthorizationApi;
import com.example.demo.entity.core.Response;
import com.example.demo.entity.httpClient.TokenResponseDTO;
import com.example.demo.entity.msg.ErrorMsg;
import com.example.demo.enums.HttpEnum;
import com.example.demo.enums.TestEnum;
import com.example.demo.handler.exceptions.CheckException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@RestController
@Api(tags = "TestController 测试视图类")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHelper httpHelper;

    @Autowired
    private AuthorizationApi authorizationApi;

    /**
     * 测试方法 test01
     * @param value
     * @return
     */
    @GetMapping("/{value}")
    @ApiOperation(value="测试方法 test01")
    public Response<Integer> test01(@PathVariable Integer value){
        if(value==0){
            return Response.fail(TestEnum.TEST_ENUM_001);
        }else if(value==1){
            throw new CheckException(TestEnum.TEST_ENUM_002);
        }else if(value==2){
            return Response.success(value);
        }else if(value==3){
            throw new RuntimeException();
        }
        return Response.success();
    }

    @PostMapping("/test02")
    @ApiOperation(value="测试方法 post test02")
    public Response<ErrorMsg> test02(@RequestBody ErrorMsg errorMsg){
        return Response.success(errorMsg);
    }


    /**
     * 测试方法 restTemplateTest
     * @return
     */
    @GetMapping("/rest_template/get")
    @ApiOperation(value="测试rest_template restTemplateTest")
    public Response<?> restTemplateTest(){
        String url="http://127.0.0.1:9001/system_error_msg/all";
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity.getBody();
        }else{
            return Response.fail(HttpEnum.HTTP_ENUM_001);
        }
    }

    @GetMapping("/rest_template/get222")
    @ApiOperation(value="测试rest_template222 restTemplateTest")
    public Response<?> restTemplateTest222(){
        String url="http://127.0.0.1:9001/system_error_msg/all";
        return httpHelper.getMapping(url,Response.class);
    }

    @GetMapping("/rest_template/post")
    @ApiOperation(value="测试rest_template02 restTemplateTest02")
    public Response<?> restTemplateTest02(){
        String url="http://127.0.0.1:9001/test/test02";
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setKey("aaaa");
        errorMsg.setEnDescription("aaaa");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "===");
        HttpEntity<ErrorMsg> request = new HttpEntity<>(errorMsg, headers);
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(url,request, Response.class);
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity.getBody();
        }else{
            return Response.fail(HttpEnum.HTTP_ENUM_001);
        }
    }


    @GetMapping("/rest_template/post333")
    @ApiOperation(value="测试rest_template0333 restTemplateTest02")
    public Response<?> restTemplateTest0333(){
        String url="https://XXXXX/openapi/oauth/token";
        HashMap<String, String> params = new HashMap<>();
        params.put("grant_type","client_credentials");
        params.put("scope","client");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "===");
        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<TokenResponseDTO> responseEntity = restTemplate.postForEntity(url,request, TokenResponseDTO.class);
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return Response.success(responseEntity.getBody());
        }else{
            return Response.fail(HttpEnum.HTTP_ENUM_001);
        }
    }


    @PostMapping("/test065")
    @ApiOperation(value="获取 token")
    public Response<?> test065(){
        return Response.success(authorizationApi.login());
    }

}
