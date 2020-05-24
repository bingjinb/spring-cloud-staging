package com.bugod.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.core.mapper.SysRoleMapper;
import com.bugod.core.service.ISysRoleService;
import com.bugod.core.service.ISysUserRoleService;
import com.bugod.core.service.ISysUserService;
import com.bugod.entity.pojo.SysRole;
import com.bugod.entity.pojo.SysUser;
import com.bugod.entity.pojo.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    ISysUserService userService;
    @Autowired
    ISysUserRoleService userRoleService;

    @Override
    public List<String> getRoleKeysByToken(String token) {
        List<String> result = new ArrayList<>();

        List<Integer> roleIds = getRoleIdsByToken(token);

        if (CollectionUtil.isNotEmpty(roleIds)) {
            LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(SysRole::getId, roleIds);
            result = list(wrapper).stream().map(SysRole::getRoleKey).collect(toList());
        }

        return result;
    }

    @Override
    public List<Integer> getRoleIdsByToken(String token) {
        SysUser sysUser = userService.getByToken(token);
        Integer userId = sysUser.getId();

        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        return userRoleService.list(queryWrapper).stream().map(SysUserRole::getRoleId).collect(toList());
    }
}
