package com.bugod.core.controller;

import com.bugod.core.service.IUserOperationRecordService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 /**
 * @Description: 用户操作轨迹记录表
 * @Author: 虫神
 * @Date:   2020-03-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="用户操作轨迹记录表")
@RestController
@RequestMapping("/core/userOperationRecord")
public class UserOperationRecordController {
	@Autowired
	private IUserOperationRecordService userOperationRecordService;
	
}
