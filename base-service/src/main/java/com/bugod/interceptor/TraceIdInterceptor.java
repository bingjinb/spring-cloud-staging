package com.bugod.interceptor;

import com.bugod.constant.APIConstant;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: TraceIdInterceptor
 * Author:   虫神
 * Date:     2020/3/5 11:58
 * Description: 用户一次请求，跟踪ID标识
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public class TraceIdInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = UUID.randomUUID().toString().replace("-","");
        MDC.put(APIConstant.TRACE_ID, traceId);
        return super.preHandle(request, response, handler);
    }
}
