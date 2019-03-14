package com.monitor.common;

/**
 * @author lisuo
 * @Title: ResultConstant
 * @ProjectName monitor
 * @Description: TODO
 * @date 2018/9/6 0006下午 10:40
 */
public enum ResultConstant {
    /**
     * 失败
     */
    FAILED(0, "failed"),

    /**
     * 成功
     */
    SUCCESS(1, "success");
    public int code;

    public String message;

    ResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
