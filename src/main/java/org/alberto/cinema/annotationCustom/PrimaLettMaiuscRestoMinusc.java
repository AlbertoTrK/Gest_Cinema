package org.alberto.cinema.annotationCustom;
import org.alberto.cinema.validator.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PrimaLettMaiuscRestoMinuscValidator.class)
public @interface PrimaLettMaiuscRestoMinusc {
	
    String message() default "Il nome deve iniziare con una lettera maiuscola";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
