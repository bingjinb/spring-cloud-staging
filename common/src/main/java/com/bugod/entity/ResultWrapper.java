package com.bugod.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "接口返回对象",description = "接口返回对象")
public class ResultWrapper<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("返回对象类型值")
    private T result;

    @ApiModelProperty("返回处理结果代码")
    private Integer code;

    @ApiModelProperty("返回处理提示信息")
    private String message;

    @ApiModelProperty("堆栈信息")
    private String stack;

    @ApiModelProperty("是否成功")
    private boolean success;


    public ResultWrapper() {
        super();
    }

    public ResultWrapper(Integer code, String message) {
        this(code, message, null);
    }

    public ResultWrapper(Integer code, String message, String stack) {
        this.code = code;
        this.message = message;
        this.stack = stack;
    }


}
