package com.bugod.interceptor;

import cn.hutool.core.util.StrUtil;
import com.bugod.annotation.Limit;
import com.bugod.constant.enums.ErrorCodeEnum;
import com.bugod.exception.ApiException;
import com.bugod.util.ApplicationContextBeanUtil;
import com.bugod.util.IpUtil;
import com.bugod.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
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
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Limit limit = handlerMethod.getMethodAnnotation(Limit.class);
            boolean flag = Objects.nonNull(limit);
            if (flag) {
                String name = limit.name();
                String prefix = limit.prefix();
                long period = limit.period();
                int count = limit.count();

                if (StrUtil.isEmpty(prefix)) {
                    String classTarget = handlerMethod.getBeanType().getName();
                    String method = handlerMethod.getMethod().getName();
                    // 设置 redis 的key：limit + 类名 + 方法名 + IP
                    prefix = new StringBuilder().append("limit_").append(classTarget).append("_").append(method).append(ip).toString();
                }

                /**
                 *
                 *  根据 prefix 的 key，获取是否存在
                 *  存在：则判断当前请求是否达到最大值 count。如果达到了，则抛出异常，否则+1；
                 *  不存在：则把prefix 作为 key，把次数和有效期一同放入缓存
                 */
                RedisUtil redisUtil = ApplicationContextBeanUtil.getBean(RedisUtil.class);
                Object value = redisUtil.get(prefix);
//                log.info("-------------> "+(value == null ? 0 : Integer.parseInt(value.toString())));
                if (Objects.isNull(value)) {
                    redisUtil.set(prefix, 1L, period);
                } else {
                    int countCurrent = Integer.parseInt(value.toString());
                    if (countCurrent < count) {
                        redisUtil.incr(prefix, 1L);
                    } else {
                        throw new ApiException(ErrorCodeEnum.LIMIT_ERROR);
                    }
                }
            }
        }
        return true;
    }


}
