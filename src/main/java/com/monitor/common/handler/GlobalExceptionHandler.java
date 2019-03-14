package com.monitor.common.handler;


import com.monitor.common.CommonsEnum;
import com.monitor.common.Result;
import com.monitor.common.exception.BusinessException;
import com.monitor.common.exception.UnauthorizedException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lisuo
 * @Description: 全局异常捕获
 * @date 2018/9/30 0030下午 10:50
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result defultExcepitonHandler(HttpServletRequest request, Exception e) {
        LOGGER.error(ExceptionUtils.getFullStackTrace(e));
        LOGGER.info("错误信息：" + e.toString());
        if (e instanceof UnauthenticatedException){
            return new Result(403, "用户未授权！");
        }else if (e instanceof UnauthorizedException){
            return Result.error(400, "用户名或者密码错误！");
        }else if (e instanceof BusinessException) {
            LOGGER.error(this.getClass() + "业务异常：" + e.getMessage());
            BusinessException businessException = (BusinessException) e;
            return Result.error(businessException.getCode(), businessException.getMessage());
        }else if (e instanceof AuthenticationException){
            LOGGER.error(this.getClass() + "权限异常：" + e.getMessage());
            return new Result(401, "用户未登录！");
        }
        //未知错误
        return new Result(CommonsEnum.RESPONSE_ERROR.getCode(), CommonsEnum.RESPONSE_ERROR.getName());
    }
}
