package org.alberto.cinema.exception;

import org.alberto.cinema.dto.response.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.alberto.cinema.exception.model.UtenteNonTrovatoException;

@RestControllerAdvice
public class ExceptionHandlerCustom {

	@ExceptionHandler(UtenteNonTrovatoException.class)
	public ResponseEntity<ErrorMessageDTO> getUtenteNonTrovato(UtenteNonTrovatoException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(e.getMessage()));
	}
}
