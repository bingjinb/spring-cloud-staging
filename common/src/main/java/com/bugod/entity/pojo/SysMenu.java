package com.bugod.entity.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author 虫神
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class SysMenu {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    private String target;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private Integer menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private Integer visible;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

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
