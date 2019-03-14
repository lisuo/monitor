package com.monitor.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/3:11:39
 */
public class WebUtils {
    public WebUtils() {
    }

    public static final void clearSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public static void setBasePath(HttpServletRequest request, String basePath) {
        request.getSession().setAttribute("BasePath", basePath);
    }

    public static String getRootPath(HttpServletRequest request) {
        String path = request.getContextPath();
        String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return rootPath;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ips = request.getHeader("x-forwarded-for");
        if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
            ips = request.getHeader("Proxy-Client-IP");
        }

        if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
            ips = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
            ips = request.getRemoteAddr();
        }

        String[] ipArray = ips.split(",");
        String clientIP = null;
        String[] var4 = ipArray;
        int var5 = ipArray.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String ip = var4[var6];
            if (!"unknown".equalsIgnoreCase(ip)) {
                clientIP = ip;
                break;
            }
        }

        return clientIP;
    }

    public static Cookie findCookie(HttpServletRequest request, String name) {
        if (request != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                Cookie[] var3 = cookies;
                int var4 = cookies.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    Cookie cookie = var3[var5];
                    if (cookie.getName().equals(name)) {
                        return cookie;
                    }
                }
            }
        }

        return null;
    }

    public static String findCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = findCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        addCookie(request, response, name, value, (String)null, maxAge);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, String domain, int maxAge) {
        String contextPath = request.getContextPath();
        if (contextPath == null || contextPath.isEmpty()) {
            contextPath = "/";
        }

        addCookie(request, response, name, value, domain, contextPath, maxAge);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, String domain, String contextPath, int maxAge) {
        if (request != null && response != null) {
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(maxAge);
            cookie.setSecure(request.isSecure());
            if (contextPath != null && !contextPath.isEmpty()) {
                cookie.setPath(contextPath);
            } else {
                cookie.setPath("/");
            }

            if (domain != null && !domain.isEmpty()) {
                cookie.setDomain(domain);
            }

            response.addCookie(cookie);
        }

    }

    public static void failureCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain, String contextPath) {
        if (request != null && response != null) {
            addCookie(request, response, name, (String)null, domain, contextPath, 0);
        }

    }

    public static void failureCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        String contextPath = request.getContextPath();
        if (contextPath == null || contextPath.isEmpty()) {
            contextPath = "/";
        }

        failureCookie(request, response, name, domain, contextPath);
    }

    public static void failureCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        failureCookie(request, response, name, (String)null);
    }

    public static String completeTheRequestAddress(HttpServletRequest request) {
        StringBuilder buff = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();
        if (queryString != null) {
            buff.append("?").append(queryString);
        }

        return buff.toString();
    }
}
