package com.monitor.auth.controller;



import com.monitor.auth.entity.Permission;
import com.monitor.auth.service.PermissionService;
import com.monitor.auth.vo.AddPermissionInVo;
import com.monitor.base.AbstratController;
import com.monitor.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@RestController
@RequestMapping("/permission")
@Api(value = "permission", description = "权限")
public class PermissionController extends AbstratController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/addPermission")
    @ResponseBody
    @ApiOperation(value = "addPermission", notes = "添加权限")
    public Result<String> addUser(@RequestBody @ApiParam AddPermissionInVo param) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("添加权限成功！").setData(null);
        Permission permission = new Permission();
        BeanUtils.copyProperties(param, permission);
        permission.setCreateTime(LocalDateTime.now());
        permissionService.insert(permission);
        return result;
    }

    @PostMapping("/queryUserPermission")
    @ApiOperation(value = "queryUserPermission", notes = "查询用户权限")
    @RequiresAuthentication
    public Result<String> queryUserPermission(@RequestBody @ApiParam("用户id")  Long userId) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("添加权限！");
        permissionService.queryUserPermission(userId);
        return result;
    }


    @PostMapping("/deletePermission")
    @ApiOperation(value = "deletePermission", notes = "删除用户权限")
    @RequiresAuthentication
    public Result<String> deletePermission(Long[] permissionIds) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("添加权限！");
        permissionService.deletePermissions(permissionIds);
        return result;
    }


}
