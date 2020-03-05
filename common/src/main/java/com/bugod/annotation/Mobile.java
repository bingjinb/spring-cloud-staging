package com.bugod.annotation;


import com.bugod.constant.AnnotationConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: Mobile
 * Author:   虫神
 * Date:     2020/3/4 13:49
 * Description: 手机号码校验注解
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MobileValidator.class})
public @interface Mobile {

    String message() default AnnotationConstant.MOBILE_MESSAGE;

    boolean isRequired() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
