package com.bugod.core.service;

import com.bugod.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 解密获得username，用于和数据库进行对比，根据用户名，查询表判断是否有数据
     * @param token
     * @return SysUser
     */
    SysUser getByToken(String token);

    /**
     * 验证账号密码是否正确
     * @param loginName
     * @param password
     * @return Boolean
     */
    SysUser verfyUser(String loginName, String password);
}
