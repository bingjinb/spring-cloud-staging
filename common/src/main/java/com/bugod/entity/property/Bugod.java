package com.bugod.entity.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: Bugod
 * Author:   虫神
 * Date:     2020/4/6 10:00
 * Description: application 配置文件，自定义属性集
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@ConfigurationProperties(prefix = Bugod.BUGOD_PREFIX)
@Data
public class Bugod {
    public static final String BUGOD_PREFIX = "bugod";
    /**
     * Xss配置
     */
    @NestedConfigurationProperty()
    private XSS xss;

}
