package es.uji.al259348.sliwwebmanager.model.validation.validators;

import es.uji.al259348.sliwwebmanager.model.validation.annotations.DeviceMacAvailable;
import es.uji.al259348.sliwwebmanager.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DeviceMacAvailableValidator implements ConstraintValidator<DeviceMacAvailable,String> {

    @Autowired
    DeviceService deviceService;

    @Override
    public void initialize(DeviceMacAvailable constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return !deviceService.macExists(value);

    }

}
