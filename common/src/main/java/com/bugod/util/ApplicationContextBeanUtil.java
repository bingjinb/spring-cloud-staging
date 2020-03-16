package com.bugod.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: ApplicationContextBeanUtil
 * Author:   虫神
 * Date:     2020/3/16 11:18
 * Description: 获取容器内相关信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Component
@Slf4j
public class ApplicationContextBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 利用aware注入application
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 注入application
        ApplicationContextBeanUtil.applicationContext = applicationContext;
    }

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name和class获取bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 根据clazz类型获取spring容器中的对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 根据注解类从容器中获取对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> getBeansOfAnnotation(Class<? extends Annotation> clazz) {
        return getApplicationContext().getBeansWithAnnotation(clazz);
    }
}
