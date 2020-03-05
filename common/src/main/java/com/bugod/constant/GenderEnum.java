package com.bugod.constant;

import lombok.Getter;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: GenderEnum
 * Author:   虫神
 * Date:     2020/3/3 15:02
 * Description: 性别枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Getter
public enum  GenderEnum implements BaseEnum {
    MALE(0),
    FEMALE(1);

    private Integer key;

    GenderEnum(Integer key) {
        this.key = key;
    }
}
