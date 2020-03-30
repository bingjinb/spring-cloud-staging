package com.bugod.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bugod.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
public interface ISysMenuService extends IService<SysMenu> {
//    /**
//     * 根据token获取资源集合
//     * @param roles
//     * @return list
//     */
//    List<String> getPermsByRoles(List<String> roles);

    /**
     * 根据token获取资源集合
     * 1. token 获取用户ID
     * 2. 用户ID 获取角色ID集合
     * 3. 角色ID集合 获取菜单ID集合
     * 4. 菜单ID集合 获取菜单资源集合
     *
     * @param token
     * @return list
     */
    List<String> getPermsByToken(String token);
}
