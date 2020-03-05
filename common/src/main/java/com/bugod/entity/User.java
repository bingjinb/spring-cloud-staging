package com.bugod.entity;

import com.bugod.annotation.Mobile;
import lombok.Data;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: User
 * Author:   虫神
 * Date:     2020/3/4 14:24
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Data
public class User {

    @Mobile(isRequired = true)
    String mobile;
}
