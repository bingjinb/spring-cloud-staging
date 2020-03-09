package com.bugod.entity;

import com.bugod.annotation.Mobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: User
 * Author:   虫神
 * Date:     2020/3/4 14:24
 * Description: 用户实体类
 *
 *    注解：Accessors(fluent = true), fluent 为true, chain 必为true，
 *    参考：https://blog.csdn.net/linjpg/article/details/94588483
 *  <font color="yellow" >
 *      User po = new User();
 *      po.setMobile("17777777777");
 *      po.setEmail("bingjinb@gmail.com");
 *      System.out.println(po.getMobile());
 *
 *      等于
 *      User po = new User();
 *      po.mobile(""17777777777").email("bingjinb@gmail.com");
 *      System.out.println(po.mobile());
 *  </font>
 *    <b color = "red">
 * 注意：Accessors(fluent = true)的对象,  swagger无法解析
 *    </b>
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Data
//@Accessors(fluent = true)
@Accessors(chain = true)

public class User {

    @ApiModelProperty(notes = "手机号码")
    @Mobile(isRequired = true)
    String mobile;

    @ApiModelProperty(notes = "邮箱")
    @NotNull
    String email ;
}
