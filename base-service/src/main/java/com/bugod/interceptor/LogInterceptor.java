package com.bugod.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bugod.constant.APIConstant;
import com.bugod.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: LogInterceptor
 * Author:   虫神
 * Date:     2020/3/5 11:56
 * Description: 方法日志拦截
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        Long startTime = System.currentTimeMillis();
        request.setAttribute(APIConstant.BEGIN_TIME, startTime);
        JSONObject json = new JSONObject();
        json.put(APIConstant.TRACE_ID, MDC.get(APIConstant.TRACE_ID));
        json.put(APIConstant.IP, IpUtil.getClientIpAddress(request));
        json.put(APIConstant.REQUEST_PATH, request.getRequestURI());
        json.put(APIConstant.REQUEST_BODY, map);
        request.setAttribute(APIConstant.CONSTANT, json);

        /**
         * TODO 后期 ELK 入库，在此规则上做相应调整，2020-3-5 16:05:23
         */
        log.info(APIConstant.PREFIX + APIConstant.TYPE_BEGIN + json.toJSONString());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute(APIConstant.BEGIN_TIME);
        Object apiConstant = request.getAttribute(APIConstant.CONSTANT);
        if (apiConstant instanceof JSONObject) {
            JSONObject json = (JSONObject) apiConstant;
            json.put(APIConstant.COST_TIME, System.currentTimeMillis() - startTime);
            json.put(APIConstant.RESPONSE_RESULT, request.getAttribute(APIConstant.RESPONSE_RESULT));

            /**
             * TODO 后期 ELK 入库，在此规则上做相应调整，2020-3-5 16:05:23
             */
            log.info(APIConstant.PREFIX + APIConstant.TYPE_END + json.toJSONString());
        }
        super.afterCompletion(request, response, handler, ex);
    }

}
