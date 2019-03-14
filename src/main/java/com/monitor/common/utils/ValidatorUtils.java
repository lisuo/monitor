package com.monitor.common.utils;

import com.monitor.common.exception.BusinessException;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
/**
 * @author lisuo
 * @Description: TODO
 * @date 2018/10/7 0007下午 1:36
 */
public class ValidatorUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public ValidatorUtils() {
    }

    public static void validateEntity(Object object, Class... groups) throws BusinessException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = (ConstraintViolation)constraintViolations.iterator().next();
            throw new BusinessException(constraint.getMessage());
        }
    }
}
