package com.bugod.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: Log
 * Author:   虫神
 * Date:     2020/4/18 16:28
 * Description: IP限流拦截注解
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 资源名称，用于描述接口功能
     */
    String name() default "";

    /**
     * redis key 前缀
     */
    String prefix() default "";

    /**
     * 时间范围，单位秒
     */
    long period();

    /**
     * 限制访问次数
     */
    int count();

}
