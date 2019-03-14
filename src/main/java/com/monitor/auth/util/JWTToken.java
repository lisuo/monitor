package com.monitor.auth.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description: JWTToken类
 * @author lisuo
 * @date 2018/9/29 0029下午 4:20
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return JWTUtil.getUsername(token);
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}