package com.monitor.auth.controller;



import com.baomidou.mybatisplus.plugins.Page;
import com.monitor.auth.entity.Role;
import com.monitor.auth.service.RoleService;
import com.monitor.auth.service.UserRoleService;
import com.monitor.auth.vo.RoleAddInVo;
import com.monitor.auth.vo.RoleListInVo;
import com.monitor.auth.vo.RoleUpdateInVo;
import com.monitor.base.AbstratController;
import com.monitor.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@RestController
@RequestMapping("/role")
@Api(value = "role", description = "用户角色")
public class RoleController extends AbstratController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleService roleDao;

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/addRole")
    @ApiOperation(value = "addRole", notes = "新增角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequiresAuthentication
    public Result<String> addRole(@RequestBody @ApiParam RoleAddInVo param) {
        Result<String> result = new Result().setCode(Result.OK).setMessage("添加角色成功！");
        roleService.addRole(param);
        return result;
    }


    /**
     * 角色列表
     */
    @GetMapping("/deleteRole")
    @ApiOperation(value = "deleteRole", notes = "删除角色", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Page<Role>> list(Long[] roleIds){
        roleService.deleteRole(roleIds);
        return new Result().ok(null, "删除角色成功！");
    }

    /**
     * 角色列表
     */
    @GetMapping("/roleList")
    @ApiOperation(value = "roleList", notes = "角色列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Page<Role>> select(RoleListInVo param){
        Page<Role> page = roleService.selectRoleListPage(param);
        return new Result().ok(page, "查询角色列表成功！");
    }

    /**
     * 角色信息
     */
    @GetMapping("/info/{roleId}")
    public Result info(@PathVariable("roleId") Long roleId){
        /*SysRoleEntity role = sysRoleService.selectById(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        //查询角色对应的部门
        List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(new Long[]{roleId});
        role.setDeptIdList(deptIdList);*/

        return new Result().ok(null, "删除角色成功！");
    }
    @PostMapping("/update")
    @RequiresAuthentication
    @ApiOperation(value = "修改角色")
    public Result update(@RequestBody RoleUpdateInVo role){
        //ValidatorUtils.validateEntity(role);
        roleService.update(role);
        return new Result().ok(null, "修改角色成功！");
    }
	
}
