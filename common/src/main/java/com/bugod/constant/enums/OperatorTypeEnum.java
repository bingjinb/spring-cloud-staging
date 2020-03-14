package com.bugod.constant.enums;

import lombok.Getter;

/**
 * <pre>
 * Copyright (C) 2020 江苏新日电动车股份有限公司
 * FileName: OperatorTypeEnum
 * Author:   beyond
 * Date:     2020/3/13 16:46
 * Description: 操作类型枚举（0|其它  1|后台用户  2手机端用户）
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Getter
public enum OperatorTypeEnum implements BaseEnum {
    OTHER(0),
    BACKSTAGE(1),
    APP(2);

    private Integer key;

    OperatorTypeEnum(Integer key) {
        this.key = key;
    }

    public static void main(String[] args) {
        System.out.println(OperatorTypeEnum.OTHER.getKey());
        System.out.println(OperatorTypeEnum.OTHER);
    }
}
