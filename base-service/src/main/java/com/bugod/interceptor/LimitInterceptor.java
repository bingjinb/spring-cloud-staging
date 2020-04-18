package com.bugod.interceptor;

import cn.hutool.core.util.StrUtil;
import com.bugod.annotation.Limit;
import com.bugod.annotation.Log;
import com.bugod.util.IpUtil;
import com.bugod.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: LogInterceptor
 * Author:   虫神
 * Date:     2020/4/18 16:28
 * Description: IP限流拦截，规避DoS攻击
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Slf4j
public class LimitInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtil.getClientIpAddress(request);
        if (handler instanceof HandlerMethod) {
            String orignalUrl = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
            String actionUrl = request.getRequestURI();
            if (!orignalUrl.equals(actionUrl)) {
                actionUrl = orignalUrl;
            }
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Limit limit = handlerMethod.getMethodAnnotation(Limit.class);
            boolean flag = Objects.nonNull(limit);
            if (flag) {
                String name = limit.name();
                String prefix = limit.prefix();
                int period = limit.period();
                int count = limit.count();

                if (StrUtil.isEmpty(prefix)) {
                    String classTarget = handlerMethod.getBeanType().getName();
                    String method = handlerMethod.getMethod().getName();
                    prefix = new StringBuilder().append("limit_").append(classTarget).append(".").append(method).toString();

                    /**
                     *  根据 prefix 的 key，获取是否存在
                     *  存在：则判断当前请求是否达到最大值 count。如果达到了，则抛出异常，否则+1；
                     *  不存在：则把prefix 作为 key，把次数和有效期一同放入缓存
                     */



                }

            }
        }
        return true;
    }


}
