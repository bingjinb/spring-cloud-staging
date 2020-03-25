package com.bugod.core.controller;


import com.bugod.constant.enums.ErrorCodeEnum;
import com.bugod.entity.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class BaseController {
    public ResultWrapper success() {
        return success("");
    }

    public <T> ResultWrapper<T> success(String message, T object) {
        ResultWrapper<T> resultWrapper = new ResultWrapper();
        resultWrapper.setSuccess(true);
        resultWrapper.setCode(ErrorCodeEnum.SUCCESS.getKey());
        resultWrapper.setMessage(Objects.equals(message, "") ?ErrorCodeEnum.SUCCESS.getValue():message);
        resultWrapper.setResult(object);
        return resultWrapper;
    }

    public <T> ResultWrapper<T> success(T object) {
        if(object instanceof String){
            return success("",object);
        }
        ResultWrapper<T> resultWrapper = new ResultWrapper();
        resultWrapper.setSuccess(true);
        resultWrapper.setCode(ErrorCodeEnum.SUCCESS.getKey());
        resultWrapper.setMessage(ErrorCodeEnum.SUCCESS.getValue());
        resultWrapper.setResult(object);
        return resultWrapper;
    }


    public ResultWrapper error(Integer key, String value, String stack) {
        ResultWrapper resultWrapper = new ResultWrapper(key, value, stack);
        return resultWrapper;
    }

    public ResultWrapper error(Integer key, String value) {
        ResultWrapper resultWrapper = new ResultWrapper(key, value);
        return resultWrapper;
    }

    public ResultWrapper error(String value) {
        return error(ErrorCodeEnum.FAIL.getKey(), value);
    }


    public ResultWrapper error(String value, String stack) {
        return error(ErrorCodeEnum.FAIL.getKey(), value, stack);
    }

    public ResultWrapper error(ErrorCodeEnum errorCodeEnum) {
        ResultWrapper resultWrapper = new ResultWrapper(errorCodeEnum.getKey(), errorCodeEnum.getValue());
        return resultWrapper;
    }

    public ResultWrapper error(ErrorCodeEnum errorCodeEnum, String stack) {
        ResultWrapper resultWrapper = new ResultWrapper(errorCodeEnum.getKey(), errorCodeEnum.getValue(), stack);
        return resultWrapper;
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultWrapper unAuthorized() {
        return error(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

}
