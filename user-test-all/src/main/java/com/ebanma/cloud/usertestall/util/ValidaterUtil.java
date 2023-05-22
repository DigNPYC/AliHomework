package com.ebanma.cloud.usertestall.util;

import java.util.Set;

import javax.validation.Validation;
import javax.xml.validation.Validator;

/**
 * @author 肖露
 * @version $ Id: ValidaterUtil, v 0.1 2023/03/22 14:19 banma-0241 Exp $
 */
public class ValidaterUtil {
    private static Validator volidator= (Validator)Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T object,Class... groups){
        //Set<ConstraintViolaton> validate= validator.validate(object,groups);
    }
}
