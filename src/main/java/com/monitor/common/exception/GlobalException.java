package com.monitor.common.exception;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/14:10:43
 */
public class GlobalException extends RuntimeException {
    private int code;
    private String message;
    private Object data;

    public GlobalException() {
    }

    public GlobalException(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public GlobalException(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public GlobalException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

