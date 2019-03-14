package com.monitor.auth.util;

/**
 * @Description: shiro工具类
 * @Author: lisuo
 * @Date: 2018/10/6:17:11
 */


import com.monitor.auth.entity.User;
import com.monitor.auth.shiro.RedisCacheManager;
import com.monitor.common.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**  加密算法 */
public class ShiroUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtils.class);
    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";
    /**  循环次数 */
    public final static int hashIterations = 16;
    /**
     * 密码最大重试次数
     */
    public final static int RETRY_NUM_MAX = 5;

    @Autowired
    private static CacheManager cacheManager;
    static {
        cacheManager = new RedisCacheManager();
    }

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toHex();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static User getUserEntity() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUserEntity().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        //判断redis是否存在缓存
        Subject subject = getSubject();
        Cache userInfoCache = cacheManager.getCache(subject.getPrincipal().toString());

        if (Objects.isNull(userInfoCache.get(Constant.AUTH_USER_TOKEN + subject.getPrincipal().toString()))){
            if (isLogin()){
                subject.logout();
            }
            return;
        }

        subject.logout();
    }

    public static String getKaptcha(String key) throws Exception {
        Object kaptcha = getSessionAttribute(key);
        if(kaptcha == null){
            throw new Exception("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

    public static void main(String[] args) {
        String password = sha256("1234", "tZsnKtdcFLo6mPQBjECF");
        System.out.println(Hex.decode(password));
        System.out.println(password);
        System.out.println(new SimpleHash(hashAlgorithmName, "1234", "tZsnKtdcFLo6mPQBjECF", hashIterations).toHex());

        System.out.println(ByteSource.Util.bytes("tZsnKtdcFLo6mPQBjECF"));
    }

}