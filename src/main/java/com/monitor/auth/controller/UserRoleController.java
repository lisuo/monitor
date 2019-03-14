package com.monitor.auth.controller;



import com.monitor.auth.service.UserRoleService;
import com.monitor.auth.vo.AddUserRoleInVo;
import com.monitor.auth.vo.QueryUserRoleListOut;
import com.monitor.base.AbstratController;
import com.monitor.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@RestController
@RequestMapping("/userRole")
@Api(value = "userRole", description = "用户-角色")
public class UserRoleController extends AbstratController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/addUserRole")
    @ApiOperation(value = "addUserRole", notes = "为用户添加角色")
    @RequiresAuthentication
    public Result<String> addUserRole(@RequestBody @ApiParam AddUserRoleInVo param) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("为用户添加角色成功！");
        userRoleService.saveOrUpdate(param);
        return result;
    }


    @PostMapping("/updateUserRole")
    @ApiOperation(value = "updateUserRole", notes = "更新用户角色")
    @RequiresAuthentication
    public Result<String> updateUserRole(@RequestBody @ApiParam AddUserRoleInVo param) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("更新用户角色成功！");

        return result;
    }

    @GetMapping("/queryUserRole")
    @ApiOperation(value = "queryUserRole", notes = "查询用户角色", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequiresAuthentication
    public Result<List<QueryUserRoleListOut>> queryUserRole(@ApiParam(value = "用户ID") @RequestParam Long userId) {
        Result<List<QueryUserRoleListOut>> result = new Result().setCode(Result.OK).setMessage("查询用户角色成功！");
        List<QueryUserRoleListOut> userRoleList = userRoleService.queryRoleIdList(userId);
        result.setData(userRoleList);
        LOGGER.info(result.toString());
        return result;
    }

}
