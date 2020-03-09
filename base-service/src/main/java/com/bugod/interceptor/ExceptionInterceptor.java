package com.bugod.interceptor;

import com.bugod.constant.ErrorCodeEnum;
import com.bugod.entity.ResultWrapper;
import com.bugod.exception.ApiException;
import com.bugod.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ExceptionInterceptor {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    ResultWrapper handleException(ApiException apiException) throws ServletException {
        Integer code = apiException.getCode();
        String message = apiException.getMessage();
        if (ValidatorUtil.isNullOrEmpty(code)) {
            code = ErrorCodeEnum.FAIL.getKey();
        }
        if (ValidatorUtil.isNullOrEmpty(message)) {
            message = ErrorCodeEnum.desc(code);
        }
        return bindResultWrapper(code, message, message, apiException);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    ResultWrapper handleException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> FieldErrors = bindingResult.getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError oe : FieldErrors) {
            FieldError fe = (FieldError) oe;
            String field = fe.getField();
            String defaultMessage = fe.getDefaultMessage();
            errorMessage.append(field).append("|").append(defaultMessage).append(",");
        }
        Integer code = ErrorCodeEnum.ARGS_ERROR.getKey();
        String message = errorMessage.toString();
        return bindResultWrapper(code, message, message, ex);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResultWrapper handleDefaultException(Exception ex) {
        Integer code = ErrorCodeEnum.FAIL.getKey();
        String message = ErrorCodeEnum.FAIL.getValue();
        String stack = ex.getMessage();
        return bindResultWrapper(code, message, stack, ex);
    }


    /**
     * 组装 ResultWrapper
     * @param code  返回处理结果代码
     * @param message   返回处理提示信息
     * @param stack     堆栈信息 如果为空，则把异常赋值给它
     * @param ex    异常，为空的stack赋值用
     * @return
     */
    private ResultWrapper bindResultWrapper(Integer code, String message, String stack, Exception ex) {
        if (ValidatorUtil.isNullOrEmpty(stack)) {
            stack = ex.toString();
        }
        ResultWrapper resultWrapper = new ResultWrapper(code, message, stack);
        log.info(message, ex);
        return resultWrapper;
    }
}
