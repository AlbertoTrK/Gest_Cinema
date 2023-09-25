package org.alberto.cinema.filtri;

public class FiltraFilmRequest {
	private String titolo;
	private int durata;
	private String descrizione;
	private String nomeGenere;
	private String nomeAttore;
	private String cognomeAttore;
	
	

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

	public String getNomeAttore() {
		return nomeAttore;
	}

	public void setNomeAttore(String nomeAttore) {
		this.nomeAttore = nomeAttore;
	}

	public String getCognomeAttore() {
		return cognomeAttore;
	}

	public void setCognomeAttore(String cognomeAttore) {
		this.cognomeAttore = cognomeAttore;
	}

}
