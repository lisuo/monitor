package com.monitor.common.exception;

/**
 * 身份认证异常
 * @author lisuo
 * @since 2018-09-29
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
