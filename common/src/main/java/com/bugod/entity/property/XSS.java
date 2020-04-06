package com.bugod.entity.property;

import lombok.Data;

import java.util.List;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: XSS
 * Author:   虫神
 * Date:     2020/4/6 9:59
 * Description: application 配置文件自定义属性对象 xss
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Data
public class XSS {
    /**
     * Xss开关
     */
    private Boolean enabled;

    /**
     * 排除字段
     */
    private List<String> excludeFields;
    /**
     * 排除路径
     */
    private List<String> excludeUrls;

}
