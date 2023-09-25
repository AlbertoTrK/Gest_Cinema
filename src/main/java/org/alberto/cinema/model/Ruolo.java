package org.alberto.cinema.model;

public enum Ruolo {
	UTENTE("UTENTE"),
	STAFF("STAFF"),
	ADMIN("ADMIN");
	
	private String ruolo;

	private Ruolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}
