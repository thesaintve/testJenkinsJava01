package com.microservice.nttdata.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfigurableRegexValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurableRegex {

    String message() default "No cumple con el patron";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}