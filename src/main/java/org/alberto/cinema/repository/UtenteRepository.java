package org.alberto.cinema.repository;

import java.util.List;
import java.util.Optional;

import org.alberto.cinema.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long>{
	
	public Optional <Utente> findUtenteByUsername(String username);
	public Optional <Utente> findUtenteByUsernameAndPassword(String username, String password);
	public Optional <Utente> findUtenteByEmail(String email);
	public Optional <Utente> findUtenteByUsernameAndEmail(String username, String email);
	
	//per repo test
	public List<Utente> findAllById(long idUtente);

}
