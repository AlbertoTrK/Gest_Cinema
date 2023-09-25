package org.alberto.cinema.dto.response;

public class RecensioneDTOResponse {
	public String titoloFilm;
	public String testoRecensione;
	public String usernameAutore;
	public int voto;
	
	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getTitoloFilm() {
		return titoloFilm;
	}

	public void setTitoloFilm(String titoloFilm) {
		this.titoloFilm = titoloFilm;
	}

	public String getTestoRecensione() {
		return testoRecensione;
	}

	public void setTestoRecensione(String testoRecensione) {
		this.testoRecensione = testoRecensione;
	}

	public String getUsernameAutore() {
		return usernameAutore;
	}

	public void setUsernameAutore(String usernameAutore) {
		this.usernameAutore = usernameAutore;
	}

}
