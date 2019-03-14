package com.monitor.auth.shiro;


import com.monitor.auth.dao.RoleDao;
import com.monitor.auth.dao.UserRoleDao;
import com.monitor.auth.entity.User;
import com.monitor.auth.service.RolePermissionService;
import com.monitor.auth.service.UserRoleService;
import com.monitor.auth.service.UserService;
import com.monitor.auth.util.JWTToken;
import com.monitor.auth.util.JWTUtil;
import com.monitor.common.Constant;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Description: 权限校验
 * @author lisuo
 * @date 2018/9/28 0028下午 10:52
 */
@Component
public class MyRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private CacheManager cacheManager;
    /**
     * JWT签名密钥，这里没用。我使用的是用户的MD5密码作为签名密钥
     */
    public static final String SECRET = "9281e268b77b7c439a20b46fd1483b9a";

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 认证信息(身份验证)
     * Authentication 是用来验证用户身份
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
            throws AuthenticationException {

        LOGGER.info("doGetAuthenticationInfo(): " + auth);
        String token = (String) auth.getCredentials();

        String username = JWTUtil.getUsername(token);
        if (username == null) {
            LOGGER.info("token invalid");
            throw new AuthenticationException("token invalid");
        }

        //通过username从数据库中查找 user对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUserName(username);

        if (user == null) {
            LOGGER.info("User didn't existed!");
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JWTUtil.verify(token, username, user.getPassword())) {
            LOGGER.info("用户名或者密码错误！");
            throw new AuthenticationException("用户名或者密码错误！");
        }
        //UsernamePasswordToken
        return new SimpleAuthenticationInfo(user.getUsername(), token, getName());
}

    /**
     * 此方法调用hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        LOGGER.info("权限配置------------------------------------------------------------------------->MyShiroRealm.doGetAuthorizationInfo()");
        String username = principals.toString();

        Cache userCache = cacheManager.getCache(username);
        // 下面的可以使用缓存提升速度
        User user = userService.findByUserName(username);

        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        req.setAttribute("currentUser" ,user);

        
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();


        if (Objects.isNull(userCache.get(Constant.AUTH_USER_ROLES + username))){
            //查询用户角色
            Set<String> userRoles = userRoleDao.queryUserRoleIdsByUserName(username);
            authorizationInfo.addRoles(userRoles);
            userCache.put(Constant.AUTH_USER_ROLES + username, userRoles);
        }
        authorizationInfo.addRoles((Set)userCache.get(Constant.AUTH_USER_ROLES + username));
        //查询用户权限 设置相应角色的权限信息
        if (Objects.isNull(userCache.get(Constant.AUTH_USER_PERMISSIONS + username))){
            authorizationInfo.addStringPermissions(userService.getPermission(username));
        }
        authorizationInfo.addStringPermissions((List)userCache.get(Constant.AUTH_USER_PERMISSIONS + username));
        return authorizationInfo;
    }


    /*@Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        shaCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }*/
}

