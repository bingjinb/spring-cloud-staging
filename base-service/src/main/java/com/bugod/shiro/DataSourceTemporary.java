package com.bugod.shiro;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: DataSourceTemporary
 * Author:   虫神
 * Date:     2020/3/24 10:57
 * Description: 临时的Map数据源
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public class DataSourceTemporary {
    private static Map<String, Map<String, String>> data = new HashMap<>();

    static {
        Map<String, String> data1 = new HashMap<>();
        data1.put("password", "smith123");
        data1.put("role", "user");
        data1.put("permission", "view");

        Map<String, String> data2 = new HashMap<>();
        data2.put("password", "danny123");
        data2.put("role", "admin");
        data2.put("permission", "view,edit");

        data.put("smith", data1);
        data.put("danny", data2);
    }

    public static Map<String, Map<String, String>> getData() {
        return data;
    }
}
