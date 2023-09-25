package org.alberto.cinema.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SalaDTORequest {
	@NotBlank(message = "il nome della sala non può essere vuoto")
	@Size(min = 3, message = "almeno tre caratteri per nome sala")
	private String nomeSala;
	@Min(value = 1, message = "la capienza deve essere almeno 1") //ovviamente in app reale, settare value con valore ragionevole (es. 50)
	private int capienza;
	@NotNull(message = "il campo deleted non può essere vuoto")
	private boolean deleted;
	
	//costruttore vuoto implicito
	
	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
