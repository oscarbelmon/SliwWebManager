package es.uji.al259348.sliwwebmanager.model.validation.validators;

import es.uji.al259348.sliwwebmanager.model.validation.annotations.DeviceIdAvailable;
import es.uji.al259348.sliwwebmanager.model.validation.annotations.UserIdAvailable;
import es.uji.al259348.sliwwebmanager.services.DeviceService;
import es.uji.al259348.sliwwebmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DeviceIdAvailableValidator implements ConstraintValidator<DeviceIdAvailable,String> {

    @Autowired
    DeviceService deviceService;

    @Override
    public void initialize(DeviceIdAvailable constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return !deviceService.idExists(value);

    }

}
