package com.monitor.auth.dao;

import com.monitor.auth.entity.Kaptcha;
import com.monitor.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author lisuo
 * @since 2018-09-28
 */
@Mapper
public interface KaptchaDao extends BaseDao<Kaptcha> {
    /**
     * 验证码存在数据库里面
     * 验证的时候查找
     * @param remoteAddr
     * @return
     */
    Kaptcha getByIp(String remoteAddr);
}