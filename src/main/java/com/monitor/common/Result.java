package com.monitor.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

@ApiModel(description = "返回值")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Integer OK = 0;
    public static final Integer FAIL = -1;
    /**
     * 状态码：1成功，其他为失败
     */
    @ApiModelProperty(value = "返回码")
    public int code;

    /**
     * 成功为success，其他为失败原因
     */
    @ApiModelProperty(value = "返回信息")
    public String message;

    /**
     * 数据结果集
     */
    @ApiModelProperty(value = "数据")
    public T data;

    public Result() {
        this.code = OK;
    }


    public static Result error(String message){
        return error(500, message);
    }

    public static Result error(int code, String message){
        return new Result(code, message);
    }
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(T data) {
        this.data = data;
    }
    public Result ok(T t, String message){
        this.code = OK;
        this.data = data;
        this.message = message;
        return this;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}