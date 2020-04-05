package com.bugod.shiro;

import com.bugod.core.service.ISysMenuService;
import com.bugod.core.service.ISysRoleService;
import com.bugod.core.service.ISysUserService;
import com.bugod.entity.pojo.SysUser;
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

import java.util.List;

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

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String token = principalCollection.toString();
        List<String> roles = ApplicationContextBeanUtil.getBean(ISysRoleService.class).getRoleKeysByToken(token);
        List<String> perms = ApplicationContextBeanUtil.getBean(ISysMenuService.class).getPermsByToken(token);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 1. 根据用户名获取用户角色
        simpleAuthorizationInfo.addRoles(roles);
        // 2. 根据用户名获取用户资源
        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        SysUser sysUser = getUserService().getByToken(token);

        // 2. 根据用户名、密码、token解密，判断是否有效
        boolean verifyFlag = JWTUtil.verify(token, sysUser.getLoginName(), sysUser.getPassword());
        if (!verifyFlag) {
            throw new AuthenticationException();
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }


    private ISysUserService getUserService() {
        return ApplicationContextBeanUtil.getBean(ISysUserService.class);
    }
}
