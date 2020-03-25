package com.bugod.core.service.impl;

import com.bugod.core.service.IUserService;
import com.bugod.shiro.DataSourceTemporary;
import com.bugod.shiro.UserBean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: UserServiceImpl
 * Author:   虫神
 * Date:     2020/3/24 11:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public UserBean getUser(String username) {
        if (! DataSourceTemporary.getData().containsKey(username)){
            return null;
        }

        UserBean user = new UserBean();
        Map<String, String> detail = DataSourceTemporary.getData().get(username);

        user.setUsername(username);
        user.setPassword(detail.get("password"));
        user.setRole(detail.get("role"));
        user.setPermission(detail.get("permission"));
        return user;
    }
}
