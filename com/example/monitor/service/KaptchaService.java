package {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.service;

import com.baomidou.mybatisplus.service.IService;
import {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.entity.${className}Entity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author lisuo
 * @email ${email}
 * @date ${datetime}
 */
public interface ${className}Service extends IService<${className}Entity> {

}

