package org.alberto.cinema.dto.request;

import org.alberto.cinema.annotationCustom.PrimaLettMaiuscRestoMinusc;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GenereDTO {
	
	@NotBlank(message = "il nome del genere non può essere vuoto")
	@Size(min = 3, message = "inserire almeno tre caratteri per il genere")
//	@PrimaLettMaiuscRestoMinusc(message = "la prima lettera del nome del genere deve essere maiuscolo, il resto minuscolo")
	private String name;
	@NotNull(message = "il campo deleted non può essere vuoto")
	private boolean deleted;
	
	public GenereDTO() {

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
