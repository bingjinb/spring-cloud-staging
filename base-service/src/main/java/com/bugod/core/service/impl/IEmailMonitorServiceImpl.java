package com.bugod.core.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
import com.bugod.annotation.EmailMonitor;
import com.bugod.core.service.IEmailMonitorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: EmailMonitorServiceImpl
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
@Service
public class IEmailMonitorServiceImpl implements IEmailMonitorService {

    @Async
    @Override
    public void monitorHandle(HandlerMethod handlerMethod, String title, String emailContent, boolean isSuccess) {
        EmailMonitor emailMonitor = handlerMethod.getMethodAnnotation(EmailMonitor.class);

        boolean flagEmail = Objects.nonNull(emailMonitor);
        if (flagEmail) {
            String email = emailMonitor.email();
            if (!isSuccess && StrUtil.isNotEmpty(email)) {
                MailUtil.sendText(email, title, emailContent);
            }
        }
    }
}
