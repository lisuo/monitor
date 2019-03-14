package {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.controller;

import java.util.Arrays;
import java.util.Map;

import com.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.demo.base.AbstratController;
import {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.entity.${className}Entity;
import {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.service.${className}Service;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ${comments}
 *
 * @author lisuo
 * @email ${email}
 * @date ${datetime}
 */
@RestController
@RequestMapping("${moduleName}/${pathName}")
@Api(value = "${pathName}", description = "${comments}")
public class ${className}Controller extends AbstratController{
    @Autowired
    private ${className}Service ${classname}Service;

    /**
     * 列表
     */

    @RequiresAuthentication
    @ApiOperation(value = "list", notes = "列表查询")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Result list(@RequestParam Map<String, Object> params){
        return  new Result().ok(null, "");
    }


    /**
     * 保存
     */
    @RequiresAuthentication
    @ApiOperation(value = "save", notes = "保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody ${className}Entity ${classname}){
        ${classname}Service.insert(${classname});

        return  new Result().ok(null, "");
    }

    /**
     * 修改
     */
    @RequiresAuthentication
    @ApiOperation(value = "update", notes = "修改")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody ${className}Entity ${classname}){
        ${classname}Service.updateAllColumnById(${classname});//全部更新
        return  new Result().ok(null, "");
    }

    /**
     * 删除
     */
    @RequiresAuthentication
    @ApiOperation(value = "delete", notes = "删除")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(@RequestBody ${pk.attrType}[] ${pk.attrname}s){
        ${classname}Service.deleteBatchIds(Arrays.asList(${pk.attrname}s));
        return  new Result().ok(null, "");
    }

}
