package com.monitor.common.aspect;

import com.monitor.common.exception.BusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Description: 参数校验切面
 * @author lisuo
 * @date 2018/10/9 0009下午 11:23
 */
@Aspect
@Component
public class ParamValidAspect {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    @Pointcut("@annotation(com.monitor.auth.annotations.ParamValid)")
    public void paramValid() {
    }

    @Before("paramValid()")
    public void before(JoinPoint joinpoint) {
        logger.error(String.valueOf(joinpoint.getArgs()));
        //获取参数，如果没参数，则无需校验
        Object[] objects = joinpoint.getArgs();
        if (objects.length > 0) {
            //获得切入目标对象
            Object target = joinpoint.getThis();
            // 获得切入的方法
            Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();
            // 执行校验，获得校验结果
            Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, objects);
            if (!validResult.isEmpty()) {//如果有校验不通过的进行处理
                //获得方法的参数名称
                String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
                validResult.stream().forEach(constraintViolation ->{
                    //获得校验的参数路径信息
                    PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
                    //获得校验的参数位置
                    int paramIndex = pathImpl.getLeafNode().getParameterIndex();
                    //获得校验的参数名称
                    String paramName = parameterNames[paramIndex];
                    //封装成需要的字段
                    logger.error(paramName + ":" + constraintViolation.getMessage());
                    throw new BusinessException(constraintViolation.getMessage());
                });
            }
        }
    }


    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }
}
