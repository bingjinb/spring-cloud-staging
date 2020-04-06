package com.bugod.config;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.bugod.entity.property.Bugod;
import com.bugod.entity.property.XSS;
import com.bugod.util.ApplicationContextBeanUtil;
import com.bugod.util.ValidatorUtil;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: GlobalRequestWrapper
 * Author:   虫神
 * Date:     2020/4/6 9:49
 * Description: 全局过滤器请求处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public class GlobalRequestWrapper extends HttpServletRequestWrapper {

    public GlobalRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = htmlEscape(name, values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value == null) {
            return null;
        }
        return htmlEscape(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(name);
        if (value instanceof String) {
            value = htmlEscape(name, (String) value);
        }
        return value;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return htmlEscape(name, value);
    }

    @Override
    public String getQueryString() {
        String value = super.getQueryString();
        if (value == null) {
            return null;
        }
        return htmlEscape(value);
    }

    /**
     * 使用spring HtmlUtils 转义html标签达到预防xss攻击效果
     *
     * @param str
     * @see org.springframework.web.util.HtmlUtils#htmlEscape
     */
    protected String htmlEscape(String str) {
        XSS xss = ApplicationContextBeanUtil.getBean(Bugod.class).getXss();
        if (xss == null) {
            return HtmlUtils.htmlEscape(str);
        }
        if (!xss.getEnabled()) {
            return str;
        }
        List<String> excludeUrls = xss.getExcludeUrls();
        if (CollectionUtils.isNotEmpty(excludeUrls)) {
            String url = getServletPath();
            for (String pattern : excludeUrls) {
                Pattern p = Pattern.compile("^" + pattern);
                Matcher m = p.matcher(url);
                if (m.find()) {
                    return str;
                }
            }
        }
        return HtmlUtils.htmlEscape(str);
    }

    /**
     * 使用spring HtmlUtils 转义html标签达到预防xss攻击效果
     *
     * @param field
     * @param str
     * @see org.springframework.web.util.HtmlUtils#htmlEscape
     */
    protected String htmlEscape(String field, String str) {
        Bugod bugod = ApplicationContextBeanUtil.getBean(Bugod.class);
        XSS xss = bugod.getXss();
        if (xss == null) {
            return htmlEscape(str);
        } else {
            List<String> excludeFields = xss.getExcludeFields();
            if (ValidatorUtil.isNullOrEmpty(excludeFields)) {
                return htmlEscape(str);
            } else {
                return str.contains(field) ? str : htmlEscape(str);
            }
        }
    }

}
