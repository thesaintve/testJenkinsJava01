package com.microservice.nttdata.validators;

import org.springframework.beans.factory.annotation.Value;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfigurableRegexValidator implements ConstraintValidator<ConfigurableRegex, String> {

    // It is nitialize with default value if YML is not pressent
    @Value("${validation.password-pattern}")
    private String regex = "^(?=(?:.*?[A-Z]){1})(?=.*[0-9].*[0-9])(?=.*[a-z]).{8,12}$";

    // It is nitialize with default value if YML is not pressent
    @Value("${validation.password-pattern-msg}")
    private String regexMsg = "El password debe tener Sólo una letra mayúscula, dos números y un rango de 8 a 12 caracteres";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isValid = value != null && value.matches(regex);

        if (!isValid) {
            context.buildConstraintViolationWithTemplate(regexMsg).addConstraintViolation();
        }

        return isValid;
    }
}