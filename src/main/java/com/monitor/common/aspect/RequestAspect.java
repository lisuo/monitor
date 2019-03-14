package com.monitor.common.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author lisuo
 * @Description: TODO
 * @date 2018/10/9 0009上午 12:16
 */
@Aspect
@Component
public class RequestAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAspect.class);

    ThreadLocal<LocalDateTime> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.monitor.auth.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable{
        //方法执行开始时间
        startTime.set(LocalDateTime.now());

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        LOGGER.info("请求地址 : " + request.getRequestURL().toString());
        LOGGER.info("请求方式 : " + request.getMethod());
        LOGGER.info("IP : " + request.getRemoteAddr());
        LOGGER.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        for (Object obj :joinPoint.getArgs()) {
            if (!(obj instanceof HttpServletResponse || obj instanceof HttpServletRequest
                    || obj instanceof MultipartFile || obj instanceof StandardMultipartHttpServletRequest)){
                LOGGER.info("请求参数 : " + JSON.toJSONString(obj));
            }
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void afterReturn(Object ret){
        LOGGER.info("方法执行时间：" + ChronoUnit.SECONDS.between(startTime.get(), LocalDateTime.now()) + "毫秒");

    }


}
