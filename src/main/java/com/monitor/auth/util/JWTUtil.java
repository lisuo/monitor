package com.monitor.auth.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.monitor.auth.shiro.RedisCacheManager;
import com.monitor.common.Constant;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


/**
 * @Description: JWT工具类
 * @Author: lisuo
 * @Date: 2018/9/28:14:51
 */
public class JWTUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);
    // 过期时间30分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;


    @Autowired
    private static CacheManager cacheManager;
    static {
        cacheManager = new RedisCacheManager();
    }
    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            LOGGER.info("secret: " + secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();

            Cache userCache = cacheManager.getCache(username);
            //将token和redis中存储的token比较，多次登录前一次的token失效
            Object cacheToken = userCache.get(Constant.AUTH_USER_TOKEN + username);
            LOGGER.info("redis_token: " + cacheToken);
            if (!token.equals(cacheToken)){
                return false;
            }
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,30min后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                //.withClaim("password", secret)
                .withExpiresAt(date)
                .sign(algorithm);

    }
    
    
    public static void main(String[] args) {
        String pa = ShiroUtils.sha256("1234","EzZg5hYRgHLTsbqPVfJK");
        System.out.println(pa);
        //String sign = JWTUtil.sign("bruce", "1234");
    	String sign = JWTUtil.sign("bruce", pa);
        System.out.println(sign);
        boolean verify = JWTUtil.verify(sign,"bruce", pa);
        System.out.println(verify);
    }
    
}