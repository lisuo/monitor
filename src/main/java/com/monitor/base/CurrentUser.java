package com.monitor.base;


import com.monitor.auth.entity.User;
import com.monitor.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description: TODO
 * @author lisuo
 * @date 2018/12/13 0013上午 12:48
 */
public class CurrentUser {

    @Autowired
    private static UserService userService;
    /**
     * 获取当前用户
     * @return
     */
    public static User getCurrentUser(){
        User user = null;
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        if (Objects.nonNull(req)){
            user = (User)req.getAttribute("currentUser");
        }
        return user;
    }
}
