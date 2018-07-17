package com.jeff.gois.common.enums;

public enum ResultEnum {
    UNKONW_ERRO(-1, "未知错误"),
    USER_EXITS_ERROR(1, "用户已存在"),
    PARAMETER_ERROR(1, "参数错误"),
    SUCCESS(0, "SUCCESS"),
    SOLR_ERROR(100, "solr服务异常"),
    SYSTEM_ERROR(101, "系统错误")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
