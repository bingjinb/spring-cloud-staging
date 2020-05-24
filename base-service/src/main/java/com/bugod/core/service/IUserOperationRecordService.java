package com.bugod.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bugod.entity.pojo.UserOperationRecord;

/**
 * @Description: 用户操作轨迹记录表
 * @Author: 虫神
 * @Date: 2020-03-14
 * @Version: V1.0
 */
public interface IUserOperationRecordService extends IService<UserOperationRecord> {

    /**
     * <pre>
     *    异步使用规则：
     *   1. 项目启动异步操作，BaseApplication 加 @EnableAsync
     *   2. 异步类调用的异步方法，必须在另一个类里，加上@Async。即：当前类调用自己的异步方法没有效果
     *   3. 当前返回值必须为 Boolean 而不能是 boolean，否则会报错：org.springframework.aop.AopInvocationException: <br>
     *       Null return value from advice does not match primitive return type for:
     *
     * </pre>
     *
     * @param po
     * @return
     */
    Boolean asyncSave(UserOperationRecord po);
}
