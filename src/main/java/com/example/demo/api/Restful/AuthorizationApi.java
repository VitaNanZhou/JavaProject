package com.example.demo.api.Restful;

import com.example.demo.entity.httpClient.TokenResponseDTO;


public interface AuthorizationApi {
    /**
     * 登录
     * @return
     */
    TokenResponseDTO login();

    /**
     * 获取AccessToken
     * @return
     */
    String getAccessToken();
}
