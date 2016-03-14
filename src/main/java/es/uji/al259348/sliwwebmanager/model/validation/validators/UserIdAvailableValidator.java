package es.uji.al259348.sliwwebmanager.model.validation.validators;

import es.uji.al259348.sliwwebmanager.model.validation.annotations.UserIdAvailable;
import es.uji.al259348.sliwwebmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdAvailableValidator implements ConstraintValidator<UserIdAvailable,String> {

    @Autowired
    UserService userService;

    @Override
    public void initialize(UserIdAvailable constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return !userService.idExists(value);

    }

}
