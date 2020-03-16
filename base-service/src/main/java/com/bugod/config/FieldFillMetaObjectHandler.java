package com.bugod.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: FieldFillMetaObjectHandler
 * Author:   虫神
 * Date:     2020/3/16 12:17
 * Description: 字段填充对象处理
 *     1. 创建时间，更新时间，是否删除标识，DB设置还是在填充设置根据实际情况定
 *     2. 此处生效规则必须在字段上加注解，如下：@TableField(file=FieldFill.INSERT)
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public class FieldFillMetaObjectHandler implements MetaObjectHandler {

    private final String createBy = "createBy";
    private final String updateBy = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, createBy, String.class, currentLoginName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictInsertFill(metaObject, updateBy, String.class, currentLoginName());
    }

    /**
     * TODO 获取当前用户名 2020-3-16 12:18:15
     *
     * @return
     */
    private String currentLoginName() {
        return "root";
    }
}
