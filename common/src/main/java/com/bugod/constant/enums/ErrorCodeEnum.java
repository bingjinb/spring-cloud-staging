package com.bugod.constant.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum ErrorCodeEnum {
    SUCCESS(0, "success"),
    ARGS_ERROR(901, "参数验证错误"),
    ARGS_INVALID(902, "参数非法"),
    ARGS_NULL(903, "参数不能为空"),

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
