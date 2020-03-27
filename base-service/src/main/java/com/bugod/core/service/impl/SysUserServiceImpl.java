package com.bugod.core.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.constant.enums.ErrorCodeEnum;
import com.bugod.core.mapper.SysUserMapper;
import com.bugod.core.service.ISysUserService;
import com.bugod.entity.SysUser;
import com.bugod.exception.ApiException;
import com.bugod.util.JWTUtil;
import com.bugod.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser getByToken(String token) {
        String loginName = JWTUtil.getUsername(token);
        if (loginName == null) {
            throw new ApiException(ErrorCodeEnum.RBAC_TOKEN_INVALID);
        }

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser::getStatus, 0).eq(SysUser::getLoginName, loginName);
        SysUser result = getOne(queryWrapper);

        if (result == null) {
            throw new ApiException(ErrorCodeEnum.RBAC_USER_NOT_EXIST);
        }
        return result;
    }

    @Override
    public SysUser verfyUser(String loginName, String password) {

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getLoginName, loginName);
        SysUser po = this.getOne(wrapper);
        if (Validator.isEmpty(po)) {
            throw new ApiException(ErrorCodeEnum.RBAC_USER_NOT_EXIST);
        }

        String toPassword = po.getPassword();
        String salt = po.getSalt();
        Integer status = po.getStatus();
        if (Objects.equals(status, 1)) {
            throw new ApiException(ErrorCodeEnum.RBAC_USER_FORBIDDEN);
        }

        String fromPassword = SecurityUtil.MD5(password + salt);
        if (Objects.equals(fromPassword, toPassword)) {
            return po;
        } else {
            throw new ApiException(ErrorCodeEnum.RBAC_USER_PASSWORD_ERROR);
        }
    }
}
