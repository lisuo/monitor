package com.monitor.auth.controller;



import com.monitor.auth.entity.RolePermission;
import com.monitor.auth.service.RolePermissionService;
import com.monitor.auth.vo.RolePermissionInVo;
import com.monitor.base.AbstratController;
import com.monitor.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@Api(value = "rolePermission", description = "角色-权限")
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController extends AbstratController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/addRolePermission")
    @ApiOperation(value = "addRolePermission", notes = "添加角色权限")
    @RequiresAuthentication
    public Result<String> addRolePermission(@RequestBody @ApiParam RolePermissionInVo param) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("添加权限成功！");
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(param, rolePermission);
        rolePermission.setCreateTime(LocalDateTime.now());
        rolePermissionService.insert(rolePermission);
        return result;
    }

    @PostMapping("/queryRolePermission")
    @ApiOperation(value = "queryRolePermission", notes = "查询角色权限")
    @RequiresAuthentication
    public Result<String> queryRolePermission(@RequestBody @ApiParam("角色id")  Long roleId) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("查询角色权限成功！");
        return result;
    }

}
