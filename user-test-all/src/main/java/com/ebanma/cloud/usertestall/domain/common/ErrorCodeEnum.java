package com.ebanma.cloud.usertestall.domain.common;

public enum ErrorCodeEnum {
    SUCCESS("00000", "成功"),
    FAIL("A0400", "请求参数错误"),
    SYSTEM_ERROR("B0001", "系统执行错误"),
    RATE_LIMIT_ERROR("3005", "限流异常"),
    FILE_UPLOAD_FAIL("3006", "文件上传失败");
    private final String code;
    private final String desc;
    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
