package com.example.demo.enums;


public enum HttpEnum implements EnumInterfase<HttpEnum> {
    HTTP_ENUM_001("HTTP_ENUM_001","http request error","HTTP 请求失败"),
    HTTP_ENUM_002("HTTP_ENUM_002","authentication failure","认证失败"),
    HTTP_ENUM_003("HTTP_ENUM_003","Remote access response result set conversion JAVA object failed","远程访问响应结果集转换JAVA对象失败");
    /**
     * 枚举key
     */
    private final String key;
    /**
     * 英文描述
     */
    private final String enDescription;
    /**
     * 中文描述
     */
    private final String zhDescription;

    HttpEnum(String key, String enDescription, String zhDescription) {
        this.key = key;
        this.enDescription = enDescription;
        this.zhDescription = zhDescription;
    }

    public String getKey() {
        return key;
    }

    public String getEnDescription() {
        return enDescription;
    }

    public String getZhDescription() {
        return zhDescription;
    }
}
