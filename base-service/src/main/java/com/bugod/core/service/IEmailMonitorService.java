package com.bugod.core.service;

import org.springframework.web.method.HandlerMethod;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: EmailMonitorService
 * Author:   虫神
 * Date:     2020/3/30 10:31
 * Description: 邮件监控异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public interface IEmailMonitorService {
    /**
     * 发送邮件
     *
     * @param handlerMethod
     * @param title
     * @param emailContent
     * @param isSuccess
     */
    void monitorHandle(HandlerMethod handlerMethod, String title, String emailContent, boolean isSuccess);
}
