package com.example.demo.entity.httpClient;

import lombok.Data;


@Data
public class TokenResponseDTO {
    /**
     * access_token
     */
    private String access_token;
    /**
     * token类型
     */
    private String token_type;
    /**
     * refresh_token
     */
    private String refresh_token;
    /**
     * 过期时间（秒）
     */
    private Integer expires_in;
    /**
     * scope
     */
    private String scope;
}
