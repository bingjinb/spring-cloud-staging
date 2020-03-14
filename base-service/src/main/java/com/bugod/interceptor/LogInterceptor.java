package com.bugod.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bugod.annotation.Log;
import com.bugod.constant.APIConstant;
import com.bugod.constant.UserOperationRecordConstant;
import com.bugod.core.entity.UserOperationRecord;
import com.bugod.core.service.IUserOperationRecordService;
import com.bugod.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: LogInterceptor
 * Author:   虫神
 * Date:     2020/3/5 11:56
 * Description: 方法日志拦截
 *  <pre>
 *      参考：https://blog.csdn.net/qq_31289187/article/details/83513290
 *
 *  </pre>
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    IUserOperationRecordService operationRecordService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = MDC.get(APIConstant.TRACE_ID);
        String uri = request.getRequestURI();
        Map<String, String[]> map = request.getParameterMap();
        String ip = IpUtil.getClientIpAddress(request);
        Long startTime = System.currentTimeMillis();

        request.setAttribute(APIConstant.BEGIN_TIME, startTime);
        JSONObject json = new JSONObject();
        json.put(APIConstant.TRACE_ID, traceId);
        json.put(APIConstant.REQUEST_URI, uri);
        json.put(APIConstant.REQUEST_BODY, map);
        json.put(APIConstant.IP, ip);
        request.setAttribute(APIConstant.CONSTANT, json);


        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Log log = handlerMethod.getMethodAnnotation(Log.class);
            boolean flag = Objects.nonNull(log);
            if (flag) {
                String description = log.description();
                Integer businessType = log.businessType().getKey();
                Integer operatorType = log.operatorType().getKey();

                /**
                 * TODO userId、userName、userType 后期提供公用方法，调用获取，2020-3-14 16:16:28
                 */
                UserOperationRecord operation = new UserOperationRecord();
                operation.setActionUrl(uri)
                        .setParameter(map.toString())
                        .setDescription(description)
                        .setIp(ip)
                        .setStartTime(new Date())
                        .setBusinessType(businessType)
                        .setOperatorType(operatorType);
                request.setAttribute(UserOperationRecordConstant.CONSTANT, operation);
            }

        }

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
        Long operatingTime = System.currentTimeMillis() - startTime;
        if (apiConstant instanceof JSONObject) {
            JSONObject json = (JSONObject) apiConstant;
            json.put(APIConstant.OPERATING_TIME, operatingTime);
            json.put(APIConstant.RESPONSE_RESULT, request.getAttribute(APIConstant.RESPONSE_RESULT));

            /**
             * TODO 后期 ELK 入库，在此规则上做相应调整，2020-3-5 16:05:23
             */
            log.info(APIConstant.PREFIX + APIConstant.TYPE_END + json.toJSONString());
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Log log = handlerMethod.getMethodAnnotation(Log.class);
            boolean flag = Objects.nonNull(log);
            if (flag) {
                Object userOperationRecord = request.getAttribute(UserOperationRecordConstant.CONSTANT);
                if (userOperationRecord instanceof UserOperationRecord) {
                    UserOperationRecord operation = (UserOperationRecord) userOperationRecord;
                    operation.setOperatingTime(operatingTime).setEndTime(new Date());

                    if (ex != null) {
                        String message = ex.getMessage();
                        String errorMessage;
                        if (message == null || message.isEmpty()) {
                            errorMessage = message;
                        } else {
                            errorMessage = message.substring(0, 2000);
                        }
                        operation.setStatus(0);
                        operation.setErrorMessage(errorMessage);
                    }

                    operationRecordService.save(operation);
                }
            }

        }
        super.afterCompletion(request, response, handler, ex);
    }

}
