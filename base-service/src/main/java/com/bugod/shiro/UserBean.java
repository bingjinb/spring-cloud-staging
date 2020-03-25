package com.bugod.shiro;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: UserBean
 * Author:   虫神
 * Date:     2020/3/24 11:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Data
@Accessors(chain = true)
public class UserBean {
    private String username;

    private String password;

    private String role;

    private String permission;
}
