package com.bugod.entity.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户与岗位关联表
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class SysUserPost {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 岗位ID
     */
    private Integer postId;


}
