package org.alberto.cinema.serviceImpl;

import java.util.List;

import org.alberto.cinema.filtri.FiltraUtentiRequest;
import org.alberto.cinema.model.Utente;
import org.alberto.cinema.repository.UtenteRepository;
import org.alberto.cinema.repository.custom.UtenteCustomRepository;
import org.alberto.cinema.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository uRepo;
	
	@Autowired
	private UtenteCustomRepository uCustRepo;

	@Override
	public Utente findUtenteByUsername(String username) {
		return uRepo.findUtenteByUsername(username).orElse(null);
	}

	@Override
	public Utente login(String username, String password) {
		return uRepo.findUtenteByUsernameAndPassword(username, password).orElse(null);
	}

	@Override
	public Utente findUtenteByEmail(String email) {
		return uRepo.findUtenteByEmail(email).orElse(null);
	}

	@Override
	public void registra(Utente u) {
		/*
		Utente ut = new Utente();
		ut.setNome(u.getNome());
		ut.setCognome(u.getCognome());
		ut.setUsername(u.getUsername());
		ut.setPassword(u.getPassword());
		ut.setBiglietti(u.getBiglietti());
		ut.setEmail(u.getEmail());
		ut.setRuolo(Ruolo.UTENTE);
		ut.setDeleted(false);
		
		uRepo.save(ut);
		*/
		
		uRepo.save(u);
		
	}

	@Override
	public Utente findUtenteByUsernameAndEmail(String username, String email) {
		return uRepo.findUtenteByUsernameAndEmail(username, email).orElse(null);
	}
	
	public boolean verificaUsernameEEmail(String username, String email) {
		Utente u = uRepo.findUtenteByUsername(username).orElse(null);
		Utente u2 = uRepo.findUtenteByEmail(email).orElse(null);
		
		if(u != null) {
			return true;
		}
		else if(u2 != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Utente findById(long idUtente) {
		return uRepo.findById(idUtente).orElse(null);
	}

	@Override
	public void update(Utente u) {
		uRepo.save(u);
	}

	@Override
	public List<Utente> getUtentiFiltrati(FiltraUtentiRequest req) {
		return uCustRepo.getUtentiFiltrati(req);
	}

	@Override
	public List<Utente> findAll() {
		return uRepo.findAll();
	}
	
}
