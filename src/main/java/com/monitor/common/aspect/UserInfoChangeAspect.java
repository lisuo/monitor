package com.monitor.common.aspect;

import com.alibaba.fastjson.JSON;
import com.monitor.auth.dao.UserDao;
import com.monitor.auth.entity.User;
import com.monitor.auth.shiro.RedisCacheManager;
import com.monitor.auth.vo.RegisterUserInVo;
import com.monitor.common.Constant;
import org.apache.shiro.cache.Cache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 用户信息改变，让redis中缓存的登录信息失效
 * @Author: lisuo
 * @Date: 2018/12/13:17:17
 */
@Aspect
@Component
public class UserInfoChangeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoChangeAspect.class);
    ThreadLocal<Long> userId = new ThreadLocal<>();

    @Autowired
    private RedisCacheManager cacheManager;

    @Autowired
    private UserDao userDao;

    @Pointcut("@annotation(com.monitor.auth.annotations.UserInfoChange)")
    public void userChange() {
    }

    @Before("userChange()")
    public void deBefore(JoinPoint joinPoint) throws Throwable{

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, String[]> paramMap = request.getParameterMap();
        // 记录下请求内容
        LOGGER.info("请求地址 : " + request.getRequestURL().toString());
        LOGGER.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        MethodSignature methodSignature =  (MethodSignature)joinPoint.getSignature();
        for (Object obj :joinPoint.getArgs()) {
            if (!(obj instanceof HttpServletResponse || obj instanceof HttpServletRequest
                    || obj instanceof MultipartFile || obj instanceof StandardMultipartHttpServletRequest)){
                if (obj instanceof RegisterUserInVo){
                    RegisterUserInVo userinfo = (RegisterUserInVo)obj;
                    if (userinfo.getOperateType().equals(1)){
                        userLoginInfoExpiry (userinfo.getUsername());
                    }
                }
                if (obj instanceof Long){
                    userLoginInfoExpiry((Long)obj);
                }
                LOGGER.info("请求参数 : " + JSON.toJSONString(obj));
            }
        }
    }

    @AfterReturning(returning = "ret", pointcut = "userChange()")
    public void afterReturn(Object ret){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("用户" + "变更：");

    }

    public void userLoginInfoExpiry(Long id){
        User user = userDao.selectById(id);
        Cache cache = cacheManager.getCache(user.getUsername());
        userLoginInfoExpiry(cache, user.getUsername());
    }

    private void userLoginInfoExpiry(String username){
        Cache cache = cacheManager.getCache(username);
        userLoginInfoExpiry(cache, username);
    }

    private void userLoginInfoExpiry(Cache cache, String username){
        if (Objects.nonNull(cache)){
            cache.remove(Constant.AUTH_USER_LOGIN_RETRY_COUNT + username);
            cache.remove(Constant.AUTH_USER_TOKEN + username);
            cache.remove(Constant.AUTH_USER_ROLES + username);
            cache.remove(Constant.AUTH_USER_PERMISSIONS + username);
        }
    }
}
