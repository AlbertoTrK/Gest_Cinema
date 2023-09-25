package org.alberto.cinema.dto.response;

import java.util.List;

public class FilmDTOResponse {
	private String titolo;
	private int durata;
	private String descrizione;
	private String nomeGenere;
	private List<String> attoriDelFilm;
	
	public FilmDTOResponse() {
		
	}

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

	public String getNomeGenere() {
		return nomeGenere;
	}

	public void setNomeGenere(String nomeGenere) {
		this.nomeGenere = nomeGenere;
	}

	public List<String> getAttoriDelFilm() {
		return attoriDelFilm;
	}

	public void setAttoriDelFilm(List<String> attoriDelFilm) {
		this.attoriDelFilm = attoriDelFilm;
	}

}
