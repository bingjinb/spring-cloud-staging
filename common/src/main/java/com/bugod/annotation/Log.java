package com.bugod.annotation;

import com.bugod.constant.enums.BusinessTypeEnum;
import com.bugod.constant.enums.OperatorTypeEnum;

import java.lang.annotation.*;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: Log
 * Author:   虫神
 * Date:     2020/3/13 16:28
 * Description: 日志注解，加该注解数据入库
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 功能描述
     */
    String description() default "";

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /**
     * 操作人类别（0其它 1后台用户 2手机端用户）
     */
    OperatorTypeEnum operatorType() default OperatorTypeEnum.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean savedRequestData() default true;
}
