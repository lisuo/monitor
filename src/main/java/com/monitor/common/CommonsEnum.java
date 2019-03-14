package com.monitor.common;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/14:11:07
 */
public enum CommonsEnum {
    /**
     * 请求成功
     */
    RESPONSE_OK(0, "请求成功！"),
    /**
     * 服务器错误
     */
    RESPONSE_ERROR(500, "服务器异常，请联系管理员！"),

    /**
     * 服务器错误
     */
    RESPONSE_999(999, "未知异常！"),

    /**
     * 请求参数统一校验编码(所有参数校验错误用此编码)
     */
    RESPONSE_99001(99001, "请求参数有误！"),
    /**
     * 用户未登录
     */
    RESPONSE_99002(99002, "用户未登录！"),
    /**
     * 用户未授权
     */
    RESPONSE_99003(99003, "用户未授权！"),
    /**
     * 用户名密码错误
     */
    RESPONSE_99004(99004, "用户名密码错误！"),
    /**
     * 用户被禁用
     */
    RESPONSE_99005(99005, "用户被禁用！"),
    /**
     * 用户不存在
     */
    RESPONSE_99006(99006, "用户不存在！"),
    /**
     * 原密码错误
     */
    RESPONSE_99007(99007, "原密码错误！"),
    /**
     * 用户已存在
     */
    RESPONSE_99008(99008, "用户已存在！"),
    /**
     * 网关超时
     */
    RESPONSE_99009(99009, "网关超时！"),

    /**
     * 查询的数据为空
     */
    RESPONSE_99010(99010, "查询的数据为空！");




    CommonsEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;


    public static CommonsEnum getBycode(int code) {
        for (CommonsEnum commonsEnum : CommonsEnum.values()) {
            if (commonsEnum.getCode() == code) {
                return commonsEnum;
            }
        }
        return CommonsEnum.RESPONSE_999;
    }

    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
