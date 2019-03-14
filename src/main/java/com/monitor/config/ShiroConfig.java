package com.monitor.config;


import com.monitor.auth.filter.JWTFilter;
import com.monitor.auth.shiro.MyRealm;
import com.monitor.auth.shiro.RedisCacheManager;
import com.monitor.auth.shiro.RedisShiroSessionDAO;
import com.monitor.auth.shiro.RetryLimitHashedCredentialsMatcher;
import com.monitor.auth.util.JWTToken;
import com.monitor.auth.util.ShiroUtils;
import com.monitor.common.Constant;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.*;

/**
 * @author lisuo
 * @Description: 权限配置
 * @date 2018/9/28 0028下午 10:40
 */
@Configuration
public class ShiroConfig {
    @Value("${server.session-timeout}")
    private int tomcatTimeout;
    @Autowired
    private CacheManager cacheManager;


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/index.html", "anon");
        filterChainDefinitionMap.put("/admin.html", "anon");
        filterChainDefinitionMap.put("/**.js", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        /* swagger过滤 */
        filterChainDefinitionMap.put("/swagger**/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/user/401", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/libs/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        // 添加自己的过滤器并且取名为jwt
        Map<String, javax.servlet.Filter> filterMap = new HashMap<>(1);
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    @DependsOn("credentialsMatcher")
    public SecurityManager securityManager(CredentialsMatcher credentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealm(myRealm(credentialsMatcher));
        /*
         * 关闭shiro自带的session，详情见文档
         * * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }


    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    @Bean
    public MyRealm myRealm(CredentialsMatcher credentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        //将自定义的令牌set到了Realm
        myRealm.setCredentialsMatcher(credentialsMatcher);
        myRealm.setAuthenticationTokenClass(JWTToken.class);
        myRealm.setCachingEnabled(true);
        return myRealm;
    }


    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(RedisCacheManager cacheManager) {
        RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManager);
        //这里如果使用密码加盐验证可以去继承HashedCredentialsMatcher
        /* //加密方式
        credentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        //加密迭代次数
        credentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        //true加密用的hex编码，false用的base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);*/
        //重新尝试的次数（自己定义的）
        credentialsMatcher.setRetryMax(ShiroUtils.RETRY_NUM_MAX);
        return credentialsMatcher;
    }



    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisShiroSessionDAO redisSessionDAO() {
        RedisShiroSessionDAO redisSessionDAO = new RedisShiroSessionDAO();
        return redisSessionDAO;
    }

    @Bean
    public SessionDAO sessionDAO() {
        if (Constant.CACHE_TYPE_REDIS.equals("redis")) {
            return redisSessionDAO();
        } else {
            return new MemorySessionDAO();
        }
    }

    /**
     * shiro session的管理
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
        sessionManager.setSessionDAO(sessionDAO());
        Collection<SessionListener> listeners = new ArrayList<>();
        //HttpSessionListener
        //listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }
}

