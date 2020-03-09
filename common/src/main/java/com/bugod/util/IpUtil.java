package com.bugod.util;


import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: IpUtil
 * Author:   虫神
 * Date:     2020/3/5 12:06
 * Description: IP地址相关工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public class IpUtil {

    /**
     * @param request
     * @return
     * @description: 如果通过了多级反向代理的话，
     * X-Forwarded-For的值并不止一个， 而是一串IP值，
     * 究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取 X-Forwarded-For中第一个非unknown的有效IP字符串。
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        // nginx代理获取的真实用户ip
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        /*
          对于通过多个代理的情况， 第一个IP为客户端真实IP,多个IP按照','分割 "***.***.***.***".length() =
          15
         */
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

}
