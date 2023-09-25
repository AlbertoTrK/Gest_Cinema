package org.alberto.cinema.dto.response;

import java.util.List;

public class AttoreDTOResponse {
	
	private String nome;
	private String cognome;
	private List<FilmDTOResponse> filmDellAttore;
	
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

	public List<FilmDTOResponse> getFilmDellAttore() {
		return filmDellAttore;
	}

	public void setFilmDellAttore(List<FilmDTOResponse> filmDellAttore) {
		this.filmDellAttore = filmDellAttore;
	}

}
