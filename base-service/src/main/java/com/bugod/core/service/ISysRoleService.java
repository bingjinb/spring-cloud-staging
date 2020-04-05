package com.bugod.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bugod.entity.pojo.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据token获取角色名
     * @param token
     * @return list
     */
    List<String> getRoleKeysByToken(String token);

    /**
     * 根据token获取角色ID
     * 1. token 获取用户ID
     * 2. 用户ID 获取角色ID集合
     *
     * @param token
     * @return list
     */
    List<Integer> getRoleIdsByToken(String token);
}
