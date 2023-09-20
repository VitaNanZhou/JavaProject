package com.example.demo.api;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.TypeReference;
import com.example.demo.enums.HttpEnum;
import com.example.demo.handler.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;


@Component
public class HttpHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${oauth.token}")
    private String token;

    private final static String AUTHORIZATION="Authorization";
    private final static String OAUTH_BEARER="Basic ";
    private final static String ACCESS_TOKEN_BEARER="Bearer ";

    /**
     * Base64加密后的认证码
     * @return
     */
    public String getCompanyToken(){
        return token;
    }

    /**
     * 获取认证请求头
     * @return
     */
    public HttpHeaders getDefualtOAuthHttpHeaders(){
        return getOAuthHttpHeaders(token);
    }

    /**
     * 获取认证请求头
     * @param token  Base64加密后的认证码
     * @return
     */
    public HttpHeaders getOAuthHttpHeaders(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, OAUTH_BEARER + token);
        return headers;
    }

    /**
     * 获取access_token求头
     * @param token  access_token
     * @return
     */
    public HttpHeaders getTokenHttpHeaders(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, ACCESS_TOKEN_BEARER + token);
        return headers;
    }

    /**
     * get 请求头
     * @param url url
     * @param clazz 响应类型
     * @return
     * @param <T>
     */
    public <T> T getMapping(String url,Class<T> clazz){
        return universalProcessor(url,()->restTemplate.getForEntity(url, clazz));
    }

    /**
     * get 请求头
     * @param url url
     * @param headers 请求头
     * @param clazz 响应类型
     * @return
     * @param <T>
     */
    public <T> T getMapping(String url, HttpHeaders headers,Class<T> clazz){
        return universalProcessor(url,()->{
            HttpEntity<?> request = new HttpEntity<>(null, headers);
            return restTemplate.exchange(url,HttpMethod.GET,request,clazz);
        });
    }

    /**
     * get 请求头
     * @param url url
     * @param headers 请求头
     * @param typeReference 响应类型
     * @return
     * @param <T>
     */
    public <T> T getMapping(String url, HttpHeaders headers, TypeReference<T> typeReference){
        return universalProcessor(url, typeReference,()->{
            HttpEntity<?> request = new HttpEntity<>(null, headers);
            return restTemplate.exchange(url,HttpMethod.GET,request,String.class);
        });
    }

    /**
     * post请求
     * @param url url
     * @param headers 请求头
     * @param params 参数
     * @param clazz 响应类型
     * @return
     * @param <T>
     * @param <PARAM>
     */
    public <T,PARAM> T postMapping(String url, HttpHeaders headers,PARAM params,Class<T> clazz){
        return universalProcessor(url,()->{
            HttpEntity<PARAM> request = new HttpEntity<>(params, headers);
            return restTemplate.postForEntity(url,request,clazz);
        });
    }

    /**
     * post请求
     * @param url url
     * @param headers 请求头
     * @param params 参数
     * @param typeReference 响应类型
     * @return
     * @param <T>
     * @param <PARAM>
     */
    public <T,PARAM> T postMapping(String url, HttpHeaders headers, PARAM params, TypeReference<T> typeReference){
        return universalProcessor(url,typeReference,()->{
            HttpEntity<PARAM> request = new HttpEntity<>(params, headers);
            return restTemplate.postForEntity(url,request,String.class);
        });
    }

    /**
     * delete请求
     * @param url url
     * @param headers 请求头
     * @param params 参数
     * @param clazz 响应类型
     * @return
     * @param <T>
     * @param <PARAM>
     */
    public <T,PARAM> T deleteMapping(String url, HttpHeaders headers,PARAM params,Class<T> clazz){
        return universalProcessor(url,()->{
            HttpEntity<PARAM> request = new HttpEntity<>(params, headers);
            return restTemplate.exchange(url,HttpMethod.DELETE,request,clazz);
        });
    }

    /**
     * delete请求
     * @param url url
     * @param headers 请求头
     * @param params 参数
     * @param typeReference 响应类型
     * @return
     * @param <T>
     * @param <PARAM>
     */
    public <T,PARAM> T deleteMapping(String url, HttpHeaders headers,PARAM params, TypeReference<T> typeReference){
        return universalProcessor(url,typeReference,()->{
            HttpEntity<PARAM> request = new HttpEntity<>(params, headers);
            return restTemplate.exchange(url,HttpMethod.DELETE,request,String.class);
        });
    }

    /**
     * 统一处理http请求方法
     */
    private <T> T universalProcessor(String url, Supplier<ResponseEntity<T>> fn){
        try {
            ResponseEntity<T> responseEntity = fn.get();
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                return responseEntity.getBody();
            }else{
                throw new BusinessException(HttpEnum.HTTP_ENUM_001, StrUtil.format("{} 接口访问失败", url));
            }
        }catch (Exception e){
            throw new BusinessException(HttpEnum.HTTP_ENUM_001, StrUtil.format("{} 接口访问失败", url));
        }
    }

    /**
     * 统一处理http请求方法
     */
    private <T> T universalProcessor(String url, TypeReference<T> typeReference, Supplier<ResponseEntity<String>> fn){
        try {
            ResponseEntity<String> responseEntity = fn.get();
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                return JSON.parseObject(responseEntity.getBody(), typeReference);
            }else{
                throw new BusinessException(HttpEnum.HTTP_ENUM_001, StrUtil.format("{} 接口访问失败", url));
            }
        }catch (JSONException jsonException){
            throw new BusinessException(HttpEnum.HTTP_ENUM_003, StrUtil.format("{} 接口响应结果集转换JAVA对象失败", url));
        }catch (Exception e){
            throw new BusinessException(HttpEnum.HTTP_ENUM_001, StrUtil.format("{} 接口访问失败", url));
        }
    }
}
