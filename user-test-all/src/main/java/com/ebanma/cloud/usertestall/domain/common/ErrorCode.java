package com.ebanma.cloud.usertestall.domain.common;

/**
 * @author 肖露
 * @version $ Id: ErrorCode, v 0.1 2023/03/17 10:22 banma-0241 Exp $
 */
public enum ErrorCode {
    //错误码
    SUCCESS("00000", "成功"),
    PARAM_ERROR("A0400", "请求参数错误"),
    SYSTEM_ERROR("B0001", "系统执行出错"),
    ;

    private final String code;
    private final String desc;

    ErrorCode(String code, String desc) {
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
