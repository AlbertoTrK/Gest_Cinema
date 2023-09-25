package org.alberto.cinema.dto.response;

import java.time.LocalDateTime;

public class SpettacoloDTOResponse {
	
	private LocalDateTime data;
	private SalaDTOResponse sala;
	private FilmDTOResponse film;
	private double prezzo;

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public SalaDTOResponse getSala() {
		return sala;
	}

	public void setSala(SalaDTOResponse sala) {
		this.sala = sala;
	}

	public FilmDTOResponse getFilm() {
		return film;
	}

	public void setFilm(FilmDTOResponse film) {
		this.film = film;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}
