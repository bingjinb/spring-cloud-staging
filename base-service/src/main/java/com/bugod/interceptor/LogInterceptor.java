package com.bugod.interceptor;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.bugod.annotation.Log;
import com.bugod.constant.APIConstant;
import com.bugod.constant.UserOperationRecordConstant;
import com.bugod.core.service.IEmailMonitorService;
import com.bugod.core.service.IUserOperationRecordService;
import com.bugod.entity.pojo.ResultWrapper;
import com.bugod.entity.pojo.UserOperationRecord;
import com.bugod.util.ApplicationContextBeanUtil;
import com.bugod.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = MDC.get(APIConstant.TRACE_ID);
        String uri = request.getRequestURI();
        Map<String, String[]> map = request.getParameterMap();
        String ip = IpUtil.getClientIpAddress(request);

        UserOperationRecord po = new UserOperationRecord();
        if (handler instanceof HandlerMethod) {
            po.setActionUrl(uri).setParameter(JSONObject.toJSONString(map)).setIp(ip).setStartTime(new Date());
            preHandleUserOperationRecord((HandlerMethod) handler, po);
        }

        request.setAttribute(APIConstant.TRACE_ID, traceId);
        request.setAttribute(UserOperationRecordConstant.CONSTANT, po);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Date endTime = new Date();
        UserOperationRecord po = (UserOperationRecord) request.getAttribute(UserOperationRecordConstant.CONSTANT);
        ResultWrapper responseResult = (ResultWrapper) request.getAttribute(APIConstant.RESPONSE_RESULT);
        String authorization = request.getHeader("Authorization");
        String args = JSONObject.toJSONString(request.getParameterMap());

        Date startTime = po.getStartTime();
        Long operatingTime = DateUtil.between(startTime, endTime, DateUnit.MS);
        po.setOperatingTime(operatingTime);
        po.setEndTime(endTime);

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String classTarget = handlerMethod.getBeanType().getName();
            String method = handlerMethod.getMethod().getName();
            String result = JSONObject.toJSON(responseResult).toString();
            StringBuilder sb = new StringBuilder().append("\r\n")
                    .append("[方法]").append(classTarget).append(".").append(method).append("() ").append("\r\n")
                    .append("[头]").append("Authorization: ").append(authorization).append("\r\n")
                    .append("[参数]").append(args).append("\r\n")
                    .append("[返回]").append(result).append("\r\n")
                    .append("[耗时]").append(operatingTime).append("ms\r\n");
            log.info(sb.toString());
            // 临时添加日志，到操作轨迹详情字段，赋值后清除
            po.setDetail(sb.toString());
            afterCompletionUserOperationRecord(handlerMethod, ex, po, responseResult);
        }

        super.afterCompletion(request, response, handler, ex);
    }


    /**
     * 用户操作轨迹入库 preHandle 处理
     *
     * @param handlerMethod 用户操作轨迹实体
     * @param operation     用户操作轨迹实体
     */
    private void preHandleUserOperationRecord(HandlerMethod handlerMethod, UserOperationRecord operation) {
        Log logger = handlerMethod.getMethodAnnotation(Log.class);
        boolean flag = Objects.nonNull(logger);
        if (flag) {
            String description = logger.description();
            Integer businessType = logger.businessType().getKey();
            Integer operatorType = logger.operatorType().getKey();

            operation.setDescription(description).setBusinessType(businessType).setOperatorType(operatorType);
        }
    }

    /**
     * 用户操作轨迹入库 afterCompletion 处理
     *
     * @param handlerMethod
     * @param ex
     * @param operation     用户操作轨迹实体
     */
    private void afterCompletionUserOperationRecord(HandlerMethod handlerMethod, Exception ex, UserOperationRecord operation, ResultWrapper resultWrapper) {
        Log logger = handlerMethod.getMethodAnnotation(Log.class);
        boolean flag = Objects.nonNull(logger);

        String title = new StringBuilder().append(operation.getActionUrl()).append("错误").toString();
        // TODO 操作人根据token获取，做非空判断
        String content = new StringBuilder().append("操作人:").append("root").append("  ")
                                            .append("调用链ID:").append(MDC.get(APIConstant.TRACE_ID)).append("  ")
                                            .append("报错时间").append(DateUtil.now()).append("\r\n")
                                            .append(operation.getDetail()).toString();
        IEmailMonitorService emailMonitorService = ApplicationContextBeanUtil.getBean(IEmailMonitorService.class);
        emailMonitorService.monitorHandle(handlerMethod, title, content, resultWrapper.isSuccess());
        operation.setDetail("");

        if (flag) {
            if (ex != null) {
                String message = ex.getMessage();
                String errorMessage = getMessage(message);
                operation.setStatus(0);
                operation.setErrorMessage(errorMessage);
            }
            if (!resultWrapper.isSuccess()) {
                String message = resultWrapper.getMessage();
                String stack = resultWrapper.getStack();
                if (message == null || message.isEmpty()) {
                    message = stack;
                }
                String errorMessage = getMessage(message);
                operation.setStatus(0);
                operation.setErrorMessage(errorMessage);
                operation.setDetail(getMessage(stack));
            }

            IUserOperationRecordService operationRecordService = ApplicationContextBeanUtil.getBean(IUserOperationRecordService.class);
            operationRecordService.asyncSave(operation);
        }
    }



    /**
     * 获取入库消息
     * @param message   ResultWrapper 下的 message 或 stack
     * @return
     */
    private String getMessage(String message) {
        String errorMessage;
        if (message == null || message.isEmpty()) {
            errorMessage = message;
        } else {
            int length = message.length();
            errorMessage = message.substring(0, Math.min(length, 200));
        }
        return errorMessage;
    }

}
