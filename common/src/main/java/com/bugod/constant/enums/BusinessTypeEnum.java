package com.bugod.constant.enums;

import lombok.Getter;

/**
 * <pre>
 * Copyright (C) 2020 江苏新日电动车股份有限公司
 * FileName: BusinessTypeEnum
 * Author:   beyond
 * Date:     2020/3/13 16:45
 * Description: 业务类型枚举 （0|其它  1|新增  2|修改  3|删除）
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Getter
public enum BusinessTypeEnum implements BaseEnum {
    OTHER(0),
    SAVE(1),
    UPDATE(2),
    DELETE(3);


    private Integer key;

    BusinessTypeEnum(Integer key) {
        this.key = key;
    }
}
