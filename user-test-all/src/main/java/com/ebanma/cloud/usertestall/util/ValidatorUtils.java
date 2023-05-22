package com.ebanma.cloud.usertestall.util;


import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 验证器
 *
 * @author Lenovo
 * @date 2023/03/23
 */
public class ValidatorUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    public static <T> void validate(T object, Class... groups) {
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        if (!CollectionUtils.isEmpty(validate)) {
            StringBuilder exceptionMessage = new StringBuilder();
            validate.forEach(result -> exceptionMessage.append(result.toString()));
            throw new RuntimeException(exceptionMessage.toString());
        }

    }
}
