package com.bugod.core.service.impl;

import com.bugod.entity.SysUserPost;
import com.bugod.core.mapper.SysUserPostMapper;
import com.bugod.core.service.ISysUserPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {

}
