package org.alberto.cinema.validator;

import java.util.regex.Pattern;

import org.alberto.cinema.annotationCustom.PrimaLettMaiuscRestoMinusc;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimaLettMaiuscRestoMinuscValidator implements ConstraintValidator<PrimaLettMaiuscRestoMinusc, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		final String REGEX_NOME = "^[A-Z][a-z]*$";

		if(value == null || value.isEmpty()) {
			return true;
		}
		
		return Pattern.matches(REGEX_NOME, value);
	}

}
