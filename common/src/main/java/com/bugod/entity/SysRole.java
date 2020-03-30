package com.bugod.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class SysRole {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private Integer dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 删除标识（0|未删除  1|已删除）
     */
    private Integer isDeleted;


}
