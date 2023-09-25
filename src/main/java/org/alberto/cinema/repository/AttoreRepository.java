package org.alberto.cinema.repository;

import java.util.List;

import org.alberto.cinema.model.Attore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttoreRepository extends JpaRepository<Attore, Long>{
	public Attore findAttoreByNameAndSurname(String nome, String cognome);
	
	//per repo test
	public List<Attore> findAllById(long idAttore);
}
