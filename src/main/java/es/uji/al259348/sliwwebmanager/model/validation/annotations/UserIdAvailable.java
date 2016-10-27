package es.uji.al259348.sliwwebmanager.model.validation.annotations;

import es.uji.al259348.sliwwebmanager.model.validation.validators.UserIdAvailableValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserIdAvailableValidator.class)
@Documented
public @interface UserIdAvailable {

//    String message() default "Identificador ya en uso.";
    String message() default "Identifier already in use.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}