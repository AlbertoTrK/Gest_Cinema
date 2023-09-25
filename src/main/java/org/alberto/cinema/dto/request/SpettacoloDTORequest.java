package org.alberto.cinema.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.alberto.cinema.deserializers.*;

public class SpettacoloDTORequest {
	@NotNull(message = "la data non può essere lasciata vuota")
	//@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime data;
	@Min(value = 1, message = "id film deve essere almeno 1")
	private long idFilm;
	@Min(value = 1, message = "id sala deve essere almeno 1")
	private long idSala;
	@Min(value = 1, message = "il prezzo deve essere almeno 1")
	private double prezzo;
	private boolean deleted;

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public long getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(long idFilm) {
		this.idFilm = idFilm;
	}

	public long getIdSala() {
		return idSala;
	}

	public void setIdSala(long idSala) {
		this.idSala = idSala;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
