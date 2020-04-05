package com.bugod.entity.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class SysPost {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 显示顺序
     */
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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
