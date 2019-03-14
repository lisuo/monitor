package {Entity=com.example.monitor.entity, Mapper=com.example.monitor.dao, ModuleName=example.monitor, Xml=com.example.monitor.mapperXml, ServiceImpl=com.example.monitor.serviceImpl, Service=com.example.monitor.service, Controller=com.example.monitor.controller}.${moduleName}.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.demo.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * ${comments}
 * 
 * @author lisuo
 * @email ${email}
 * @date ${datetime}
 */
@TableName("${tableName}")
public class ${className}Entity extends BaseEntity<${className}Entity> {

	private static final long serialVersionUID = 1L;

    @Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
