package com.bugod.exception;

import com.bugod.constant.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message = "";

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public ApiException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiException(ErrorCodeEnum errorCodeEnum) {
        super(ErrorCodeEnum.desc(errorCodeEnum.getKey()));
        this.code = errorCodeEnum.getKey();
        this.message = ErrorCodeEnum.desc(errorCodeEnum.getKey());
    }

}
