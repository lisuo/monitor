package {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.dao.${className}Dao;
import {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.entity.${className}Entity;
import {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.service.${className}Service;


@Service("${classname}Service")
public class ${className}ServiceImpl extends ServiceImpl<${className}Dao, ${className}Entity> implements ${className}Service {


}
