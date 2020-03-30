package com.bugod.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.core.mapper.SysMenuMapper;
import com.bugod.core.service.*;
import com.bugod.entity.SysMenu;
import com.bugod.entity.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Autowired
    ISysUserService userService;

    @Autowired
    ISysRoleService roleService;

    @Autowired
    ISysUserRoleService userRoleService;

    @Autowired
    ISysRoleMenuService roleMenuService;

//    @Override
//    public List<String> getPermsByRoles(List<String> roles) {
//        List<String> result = new ArrayList<>();
//
//        if (CollectionUtil.isEmpty(roles)) {
//            return result;
//        }
//
//        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.in(SysRoleMenu::getRoleId, roles);
//        List<Integer> menuIds = roleMenuService.list(queryWrapper).stream().map(SysRoleMenu::getMenuId).collect(toList());
//
//        if (CollectionUtil.isEmpty(menuIds)) {
//            return result;
//        }
//        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
//        wrapper.in(SysMenu::getId, menuIds);
//        result = list(wrapper).stream().map(SysMenu::getPerms).collect(toList());
//
//        return result;
//    }

    @Override
    public List<String> getPermsByToken(String token) {
        List<String> result = new ArrayList<>();
        /**
         * 1. token 获取用户ID
         * 2. 用户ID 获取角色ID集合
         */
        List<Integer> roleIds = roleService.getRoleIdsByToken(token);

        if (CollectionUtil.isNotEmpty(roleIds)) {
            // 3. 角色ID集合 获取菜单ID集合
            LambdaQueryWrapper<SysRoleMenu> queryWrapperRoleMenu = new LambdaQueryWrapper<>();
            queryWrapperRoleMenu.in(SysRoleMenu::getRoleId, roleIds);
            List<Integer> menuIds = roleMenuService.list(queryWrapperRoleMenu).stream().map(SysRoleMenu::getMenuId).collect(toList());

            if (CollectionUtil.isNotEmpty(menuIds)) {
                // 4. 菜单ID集合 获取菜单资源集合
                LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
                wrapper.in(SysMenu::getId, menuIds);
                result = list(wrapper).stream().map(SysMenu::getPerms).collect(toList());
            }

        }
        return result;
    }
}
