package com.bugod.annotation;

import com.bugod.constant.AnnotationConstant;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: MobileValidator
 * Author:   虫神
 * Date:     2020/3/4 13:51
 * Description: 手机号码检验注解处理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    Mobile mobileAnnotation;

    private static final Pattern mobile_pattern = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$" );

    @Override
    public void initialize(Mobile mobileAnnotation) {
        this.mobileAnnotation = mobileAnnotation;
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        boolean required = mobileAnnotation.isRequired();
        if (required) {
            if (StringUtils.isEmpty(mobile)) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(AnnotationConstant.MOBILE_MESSAGE_NULL).addConstraintViolation();
                return false;
            }
            Matcher m = mobile_pattern.matcher(mobile);
            return m.matches();
        } else {
            if (StringUtils.isEmpty(mobile)) {
                return true;
            } else {
                Matcher m = mobile_pattern.matcher(mobile);
                return m.matches();
            }
        }
    }
}
