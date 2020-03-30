package com.bugod.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: EmailMonitor
 * Author:   虫神
 * Date:     2020/3/28 10:26
 * Description: 监控方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EmailMonitor {
    /**
     * 接收错误信息的邮箱
     */
    String email() default "";

}
