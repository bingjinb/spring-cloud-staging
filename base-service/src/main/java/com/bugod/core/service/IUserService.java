package com.bugod.core.service;

import com.bugod.shiro.UserBean;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: IUserService
 * Author:   虫神
 * Date:     2020/3/24 11:00
 * Description: 临时组装用户业务服务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public interface IUserService {

    UserBean getUser(String username);
}
