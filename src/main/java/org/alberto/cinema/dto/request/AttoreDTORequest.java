package org.alberto.cinema.dto.request;

import org.alberto.cinema.annotationCustom.PrimaLettMaiuscRestoMinusc;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AttoreDTORequest {
	@NotBlank(message = "il nome dell'attore non può essere vuoto")
	@Size(min = 2, message = "almeno due caratteri per il nome")
//	@PrimaLettMaiuscRestoMinusc(message = "il nome dell'attore deve iniziare per maiuscola")
	private String nome;
	@NotBlank(message = "il cognome dell'attore non può essere vuoto")
	@Size(min = 2, message = "almeno due caratteri per il cognome")
//	@PrimaLettMaiuscRestoMinusc(message = "il cognome dell'attore deve iniziare per maiuscola")
	private String cognome;
	@NotNull(message = "il campo deleted non può essere vuoto")
	private boolean deleted;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
