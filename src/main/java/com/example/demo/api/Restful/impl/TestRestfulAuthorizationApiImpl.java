package com.example.demo.api.Restful.impl;

import com.example.demo.api.HttpHelper;
import com.example.demo.api.Restful.AuthorizationApi;
import com.example.demo.entity.httpClient.TokenResponseDTO;
import com.example.demo.enums.HttpEnum;
import com.example.demo.handler.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestRestfulAuthorizationApiImpl implements AuthorizationApi {

    @Autowired
    private HttpHelper httpHelper;

    @Override
    public TokenResponseDTO login() {
        String url="https://XXX";
        return httpHelper.postMapping(url,httpHelper.getDefualtOAuthHttpHeaders(),null,TokenResponseDTO.class);
    }

    @Override
    public String getAccessToken() {
        TokenResponseDTO tokenResponseDTO = this.login();
        if(tokenResponseDTO==null|| StringUtils.isEmpty(tokenResponseDTO.getAccess_token())){
            throw new BusinessException(HttpEnum.HTTP_ENUM_002);
        }
        return tokenResponseDTO.getAccess_token();
    }
}
