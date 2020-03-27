package com.bugod.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("article")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="article对象", description="article")
public class Article {

	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
	private Integer id;

    @ApiModelProperty(value = "发布者")
	private Integer userId;

    @ApiModelProperty(value = "标题")
	private String title;

    @ApiModelProperty(value = "内容")
	private Object content;

    @ApiModelProperty(value = "顺序")
	private Integer sort;

    @ApiModelProperty(value = "是否公开:1|是, -1|否")
	private Integer isPublic;

    @ApiModelProperty(value = "是否推荐:1|是, -1|否")
	private Integer recommend;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill= FieldFill.INSERT)
    private String createBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(fill= FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "删除标识（0|未删除  1|已删除）")
	private Integer isDelete;
}
