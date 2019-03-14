package com.monitor.auth.service.impl;



import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.monitor.auth.dao.KaptchaDao;
import com.monitor.auth.entity.Kaptcha;
import com.monitor.auth.service.KaptchaService;
import org.springframework.stereotype.Service;

/**
 *
 * @author lisuo
 * @since 2018-09-28
 */
@Service
public class KaptchaServiceImpl extends ServiceImpl<KaptchaDao, Kaptcha> implements KaptchaService {
	
}
