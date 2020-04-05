package com.bugod.entity.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色和部门关联表
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class SysRoleDept {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 部门ID
     */
    private Integer deptId;


}
