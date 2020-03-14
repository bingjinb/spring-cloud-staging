package com.bugod.core.service.impl;

import com.bugod.core.entity.UserOperationRecord;
import com.bugod.core.mapper.UserOperationRecordMapper;
import com.bugod.core.service.IUserOperationRecordService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户操作轨迹记录表
 * @Author: beyond
 * @Date:   2020-03-14
 * @Version: V1.0
 */
@Service
public class UserOperationRecordServiceImpl extends ServiceImpl<UserOperationRecordMapper, UserOperationRecord> implements IUserOperationRecordService {

}
