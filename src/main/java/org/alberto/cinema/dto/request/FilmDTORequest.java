package org.alberto.cinema.dto.request;

import org.alberto.cinema.dto.response.AttoreDTOResponse;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class FilmDTORequest {
	@NotBlank(message = "il nome del film non può essere vuoto")
	@Size(min = 2, message = "almeno due caratteri per il film")
	private String titolo;
	@Min(value = 60, message = "la durata deve essere almeno 60")
	private int durata;
	private String descrizione;
	@Min(value = 1, message = "l'id deve essere almeno 1")
	private long idGenere;
	@NotNull
	@Size(min = 1, message = "L'id dell'attore deve essere almeno 1")
	private List<Long> attoriDelFilm;
	@NotNull(message = "il campo deleted non può essere vuoto")
	private boolean deleted;

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getIdGenere() {
		return idGenere;
	}

	public void setIdGenere(long idGenere) {
		this.idGenere = idGenere;
	}

	public List<Long> getAttoriDelFilm() {
		return attoriDelFilm;
	}

	public void setAttoriDelFilm(List<Long> attoriDelFilm) {
		this.attoriDelFilm = attoriDelFilm;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
