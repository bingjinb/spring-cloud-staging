package com.bugod.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: JWTToken
 * Author:   虫神
 * Date:     2020/3/24 10:36
 * Description: JWT 加密、解密等
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class JWTToken implements AuthenticationToken {
    String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
