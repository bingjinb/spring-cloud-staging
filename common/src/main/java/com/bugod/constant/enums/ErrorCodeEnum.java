package com.bugod.constant.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum ErrorCodeEnum {
    SUCCESS(0, "success"),
    ARGS_ERROR(901, "参数验证错误"),
    ARGS_INVALID(902, "参数非法"),
    ARGS_NULL(903, "参数不能为空"),

    RBAC_USER_NOT_EXIST(5001, "账户不存在"),
    RBAC_USER_FORBIDDEN(5002, "账户禁用"),
    RBAC_USER_PASSWORD_ERROR(5003, "账户密码错误"),
    RBAC_TOKEN_INVALID(5004, "token失效"),
    RBAC_PERMISSION_NOT_EXIST(5005, "账号未授予相应权限"),

    REDIS_ERROR(5020, "Redis异常"),
    LIMIT_ERROR(5021, "您刷新过于频繁，请稍后再试"),



    FAIL(999 , "系统异常");


    public static String desc(Integer key) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            Integer keyArg = errorCodeEnum.key;
            if (Objects.equals(key, keyArg)) {
                return errorCodeEnum.value;
            }
        }
        return "";
    }

    ErrorCodeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    Integer key;
    String value;


}
