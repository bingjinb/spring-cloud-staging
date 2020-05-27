package com.bugod.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: GlobalFilter
 * Author:   虫神
 * Date:     2020/4/6 11:55
 * Description: 全局过滤器
 *  1. FilterRegistrationBean 添加过滤器 GlobalFilter
 *  2. GlobalFilter 过滤器添加处理过后的 ServletRequest
 *  3. GlobalRequestWrapper 添加XSS处理逻辑
 *
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "globalFilter")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            GlobalRequestWrapper requestWrapper = new GlobalRequestWrapper((HttpServletRequest) servletRequest);
            filterChain.doFilter(requestWrapper, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}
