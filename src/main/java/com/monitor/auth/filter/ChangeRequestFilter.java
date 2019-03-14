package com.monitor.auth.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: shiro过滤器包装导致取不出HttpServerletRequest这里过滤转换
 * @Author: lisuo
 * @Date: 2018/10/9
 */
@WebFilter
@Order(Integer.MAX_VALUE)
public class ChangeRequestFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeRequestFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes((HttpServletRequest) request));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
