package org.alberto.cinema.exception.model;

public class UtenteNonTrovatoException extends RuntimeException{
	
	private String msg;

	public UtenteNonTrovatoException(String msg) {
		super(msg);
	}
}
