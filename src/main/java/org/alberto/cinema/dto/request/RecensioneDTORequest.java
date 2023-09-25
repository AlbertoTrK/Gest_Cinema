package org.alberto.cinema.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecensioneDTORequest {
	@NotBlank
	@Size(min = 100, message = "inserire almeno cento caratteri per la recensione")
	private String testo;
	@Min(value = 1, message = "id film deve essere almeno 1")
	private long idFilm;
	@Min(value = 1, message = "id utente deve essere almeno 1")
	private long idUtente;
	@Min(value = 1, message = "il voto deve essere almeno 1 (max 5)")
	@Max(value = 5, message = "il voto deve essere massimo 5 (min 1)")
	private int voto;
	
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public long getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(long idFilm) {
		this.idFilm = idFilm;
	}

	public long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(long idUtente) {
		this.idUtente = idUtente;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

}
