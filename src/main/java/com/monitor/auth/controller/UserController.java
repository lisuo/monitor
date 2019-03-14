package com.monitor.auth.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.monitor.auth.annotations.ParamValid;
import com.monitor.auth.annotations.UserInfoChange;
import com.monitor.auth.entity.User;
import com.monitor.auth.service.UserService;
import com.monitor.auth.shiro.RedisCacheManager;
import com.monitor.auth.util.JWTToken;
import com.monitor.auth.util.JWTUtil;
import com.monitor.auth.util.ShiroUtils;
import com.monitor.auth.vo.QueryUserInfoOutVo;
import com.monitor.auth.vo.RegisterUserInVo;
import com.monitor.auth.vo.UserListInVo;
import com.monitor.auth.vo.UserListOutVo;
import com.monitor.base.AbstratController;
import com.monitor.common.Constant;
import com.monitor.common.Result;
import com.monitor.common.exception.UnauthorizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 *
 * @author lisuo
 * @since 2018-09-06
 */
@RestController
@RequestMapping("/user")
@Api(value = "user", description = "用户信息")
public class UserController extends AbstratController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheManager cacheManager;

    @UserInfoChange
    @PostMapping("/addUser")
    @ApiOperation(value = "addUser", notes = "新增用户")
    @RequiresPermissions("user/addUser")
    public Result<String> addUser(@ParamValid @RequestBody @ApiParam RegisterUserInVo param) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("添加用户成功！");
        LOGGER.info("param: " + param);
        userService.addUser(param);
        return result;
    }

    @GetMapping("/queryUsernameExist")
    @ApiOperation(value = "queryUsernameExist", notes = "查询用户名是否存在")
    @RequiresPermissions("user/queryUsernameExist")
    public Result<Boolean> queryUsernameExist(@ApiParam("用户名") String username) {
        Result<Boolean> result = new Result().setCode(Result.OK).setMessage("用户名不存在！");
        result.setData(false);
        if(!Objects.isNull(userService.findByUserName(username))){
            result.setData(true);
            result.setMessage("用户名已存在！");
        }
        return result;
    }

    @PostMapping("/deleteUsers")
    @ApiOperation(value = "deleteUsers", notes = "删除用户")
    @RequiresAuthentication
    public Result<String> deleteUsers(Long[] ids) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("删除用户成功！");
        userService.deleteUsers(ids);
        return result;
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "updateUser", notes = "更新用户")
    @RequiresAuthentication
    public Result<String> updateUser(@RequestBody @ApiParam RegisterUserInVo param) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("添加用户成功！");
        User user = new User();
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        BeanUtils.copyProperties(param, user);
        userService.insert(user);
        return result;
    }

    @PostMapping("/queryUserPage")
    @ApiOperation(value = "获得用户列表", notes = "列表信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequiresAuthentication
    public Result<Page<UserListOutVo>> queryUserPage(@RequestBody @ApiParam UserListInVo params) {
        Result<Page<UserListOutVo>> result = new Result().setCode(Result.OK).setMessage("查询用户成功！");

        Page<UserListOutVo> page = userService.selectUserListPage(params);
        LOGGER.info("user:" + page.getRecords());
        result.setData(page);
        return result;
    }

    @GetMapping("/queryUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequiresAuthentication
    @RequiresPermissions("user/queryUserInfo")
    public Result<QueryUserInfoOutVo> queryUserPage(@ApiParam(value = "用户ID") @RequestParam(value = "id", required = true) Long id) {

        Result<QueryUserInfoOutVo> result = new Result().setCode(Result.OK).setMessage("查询用户信息成功！");
        QueryUserInfoOutVo userInfoOutVo = userService.queryUserInfo(id);
        result.setData(userInfoOutVo);
        return result;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(HttpServletResponse httpServletResponse, @RequestBody @ApiParam User loginUser) throws Exception{
        String username = loginUser.getUsername();
        User user = userService.findByUserName(username);
        if (user.getEnable() == "1"){
            throw new UnauthorizedException("用户已经被冻结，请联系管理员解除冻结！");
        }
        if (Objects.isNull(user)){
            throw new UnauthorizedException("用户名或者密码错误！");
        }
        Map<String, Object> result = new HashMap<>();
        //原密码加密
        String encodedPassword = ShiroUtils.sha256(loginUser.getPassword(), user.getSalt());
        Subject subject = SecurityUtils.getSubject();
        //String encodedPassword = MD5Utils.md5(password);
        if (user.getPassword().equals(encodedPassword)) {
        	String token = JWTUtil.sign(username, encodedPassword);
            cacheManager.getCache(username).put(Constant.AUTH_USER_TOKEN + username, token);
            subject.login(new JWTToken(token));
        	userService.getPermission(username);
        	//将token保存在redis
            //RedisUtil.set(Constant.AUTH_USER_TOKEN + username, token);
            httpServletResponse.setHeader("token", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "token");
            result.put("user", user);
            result.put("token", token);
            return new Result(Result.OK, "登录成功！", result);
        } else {
            throw new UnauthorizedException("用户名或者密码错误！");
        }
    }


    @RequestMapping(path = "/401",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return new Result(401, "Unauthorized", null);
    }

    @UserInfoChange
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录")
    public Result<String> logout(ServletRequest request, ServletResponse response) {
        Result<String> result = new Result<>(1, "退出登录成功！");
        ShiroUtils.logout();
        return result;
    }

}
