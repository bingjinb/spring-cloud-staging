package com.bugod.config;

import com.bugod.constant.APIConstant;
import com.bugod.entity.ResultWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: ControllerReponseAdvice
 * Author:   虫神
 * Date:     2020/3/5 14:57
 * Description: 在响应体返回之前记录数据、修改数据、加密（邮箱、电话号码等隐私信息，例如：158****0215，实现这种小功能）等,使用该类时加注解@ControllerAdvice
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */

@ControllerAdvice
public class ControllerReponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (object != null && object instanceof ResultWrapper) {
            try {
                HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
                servletRequest.setAttribute(APIConstant.RESPONSE_RESULT, object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
