package com.monitor.common.exception;

/**
 * @author lisuo
 * @Description: 自定义异常
 * @date 2018/9/30 下午 10:55
 */
public class BusinessException extends RuntimeException {
    //自定义错误码
    private int code;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String msg) {
        super(msg);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
