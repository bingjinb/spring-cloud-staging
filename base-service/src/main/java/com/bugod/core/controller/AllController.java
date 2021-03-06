package com.bugod.core.controller;

import com.bugod.constant.enums.ErrorCodeEnum;
import com.bugod.constant.enums.GenderEnum;
import com.bugod.core.service.ISysUserService;
import com.bugod.core.service.IUserOperationRecordService;
import com.bugod.entity.GenderPO;
import com.bugod.entity.pojo.ResultWrapper;
import com.bugod.entity.pojo.SysUser;
import com.bugod.entity.pojo.UserOperationRecord;
import com.bugod.entity.property.Bugod;
import com.bugod.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: GenderController
 * Author:   虫神
 * Date:     2020/3/3 15:45
 * Description: 乱七八糟的Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "控制层")
public class AllController extends BaseController {

    @Autowired
    ISysUserService userService;

    @Autowired
    Bugod bugod;

    @Autowired
    IUserOperationRecordService userOperationRecordService;

    @ApiOperation(value = "Get获取枚举", notes = "Get获取枚举")
    @GetMapping("/enum")
    public ResultWrapper get(GenderPO request) {
        GenderEnum genderEnum = request.getGender();
        if (Objects.isNull(genderEnum)) {
            return error(ErrorCodeEnum.ARGS_NULL, "gender 不能为空");
        }
        return success(request);
    }

    @ApiOperation(value = "path", notes = "path")
    @PostMapping("/{uuid}")
    public ResultWrapper path(@PathVariable("uuid") String uuid) {
        return success();
    }

    @ApiOperation(value = "Get获取名字", notes = "Get获取名字")
    @GetMapping("/getName/x/y/z")
    public ResultWrapper getName(@RequestParam String name) {
        return success(name);
    }


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResultWrapper login(@RequestParam("loginName") String loginName,
                               @RequestParam("password") String password) {
        SysUser result = userService.verfyUser(loginName, password);
        String token = JWTUtil.sign(loginName, result.getPassword());
        return success("登陆成功", token);
    }

    @ApiOperation(value = "认证")
    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResultWrapper requireAuth() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return success("登录成功，您通过认证", "");
        } else {
            return success("Guest登录", "");
        }
    }

    @ApiOperation(value = "授权-角色")
    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResultWrapper requireRole() {
        return success("登陆成功，您有该角色", "");
    }

    @ApiOperation(value = "授权-资源")
    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"system:user:view", "system:role:view"})
    public ResultWrapper requirePermission() {

        return success("登陆成功，您有该角色", "");
    }

    @ApiOperation(value = "test")
    @GetMapping("/getIntArgs")
    public ResultWrapper getIntArgs(@RequestParam Integer num) {
        return success(num);
    }

//    UserOperationRecord

    @ApiOperation(value = "查询用户轨迹-测试RequestBody 入参打印问题")
    @PostMapping("/getUOR")
    public ResultWrapper getUOR(@RequestBody UserOperationRecord uor) {
        return success(userOperationRecordService.list());
    }


}
