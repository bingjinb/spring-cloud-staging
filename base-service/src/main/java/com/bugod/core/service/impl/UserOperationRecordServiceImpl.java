package com.bugod.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.core.mapper.UserOperationRecordMapper;
import com.bugod.core.service.IUserOperationRecordService;
import com.bugod.entity.UserOperationRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户操作轨迹记录表
 * @Author: 虫神
 * @Date:   2020-03-14
 * @Version: V1.0
 */
@Service
public class UserOperationRecordServiceImpl extends ServiceImpl<UserOperationRecordMapper, UserOperationRecord> implements IUserOperationRecordService {


    @Async
    @Override
    public Boolean asyncSave(UserOperationRecord po) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
//        System.out.println("=================================> time: "+new Date());
        return save(po);
    }
}
