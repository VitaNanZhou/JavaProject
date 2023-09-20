package com.example.demo.enums;


public enum TestEnum implements EnumInterfase<TestEnum> {
    TEST_ENUM_001("TEST_ENUM_001","exception 01","异常01"),
    TEST_ENUM_002("TEST_ENUM_002","exception 02","异常02");
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

    TestEnum(String key, String enDescription, String zhDescription) {
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
