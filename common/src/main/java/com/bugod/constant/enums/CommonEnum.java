package com.bugod.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: CommonEnum
 * Author:   虫神
 * Date:     2020/3/16 12:00
 * Description: 枚举常规类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public class CommonEnum {
    /**
     * 删除标识（0|未删除  1|已删除）
     */
    @Getter
    @AllArgsConstructor
    public enum IS_DELETED {
        ONE(1, "已删除"),
        ZERO(0, "未删除");

        private Integer key;
        private String value;

    }
}
