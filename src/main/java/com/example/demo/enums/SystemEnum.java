package com.example.demo.enums;


public enum SystemEnum implements EnumInterfase<SystemEnum> {
    SYSTEM_001("SYSTEM_001","Action failed","操作失败"),
    SYSTEM_002("SYSTEM_002","Service Error, Please Get in Touch with Customer Service","服务异常，请联系客服"),
    SYSTEM_003("SYSTEM_003","System Failure, Please Reach Out to Customer Support","系统出现故障，请联系客服"),
    SYSTEM_004("SYSTEM_004","Parameter cannot be empty","参数不能为空");
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


    SystemEnum(String key, String enDescription, String zhDescription) {
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
