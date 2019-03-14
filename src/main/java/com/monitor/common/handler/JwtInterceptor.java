package com.monitor.common.handler;


import com.monitor.auth.shiro.RedisCacheManager;
import com.monitor.auth.util.JWTUtil;
import com.monitor.common.Constant;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 拦截token并验证，不通过则抛出异常
 * @author lisuo
 * @date 2018/12/1 0001下午 11:35
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private static CacheManager cacheManager;
    static {
        cacheManager = new RedisCacheManager();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, token, Accept,X-Requested-With");

        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");

        response.setHeader("X-Powered-By","Jetty");*/
        //取得token
        String token = request.getHeader("token");
        try {
            String username = JWTUtil.getUsername(token);
            Cache userCache = cacheManager.getCache(username);
            userCache.put(Constant.AUTH_USER_TOKEN + username, token);
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
       /* response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("P3P", "CP=CAO PSA OUR");
        super.postHandle(request, response, handler, modelAndView);*/
    }
}

