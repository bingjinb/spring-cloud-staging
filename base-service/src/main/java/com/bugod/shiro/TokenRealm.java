package com.bugod.shiro;

import com.bugod.core.service.IUserService;
import com.bugod.exception.ApiException;
import com.bugod.util.ApplicationContextBeanUtil;
import com.bugod.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: TokenRealm
 * Author:   虫神
 * Date:     2020/3/24 10:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */

@Component
@Slf4j
public class TokenRealm extends AuthorizingRealm {



    private IUserService getUserService() {

        return ApplicationContextBeanUtil.getBean(IUserService.class);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JWTUtil.getUsername(principalCollection.toString());
        UserBean user = getUserService().getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());
        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        UserBean userBean = getUserService().getUser(username);
        if (userBean == null) {
            throw new ApiException("User didn't existed!");
        }

        boolean verifyFlag = JWTUtil.verify(token, username, userBean.getPassword());
        if (!verifyFlag) {
            throw new AuthenticationException();
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
