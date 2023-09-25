package org.alberto.cinema.service;

import java.util.List;

import org.alberto.cinema.filtri.FiltraUtentiRequest;
import org.alberto.cinema.model.Utente;

public interface UtenteService {
	
	public Utente findUtenteByUsername(String username);
	public Utente findUtenteByEmail(String email);
	public Utente findUtenteByUsernameAndEmail(String username, String email);
	public void registra(Utente u);
	public boolean verificaUsernameEEmail(String username, String email);
	public Utente login(String username, String password);
	public Utente findById(long idUtente);
	public void update(Utente u);
	public List<Utente> getUtentiFiltrati(FiltraUtentiRequest req);
	public List<Utente> findAll();

}
