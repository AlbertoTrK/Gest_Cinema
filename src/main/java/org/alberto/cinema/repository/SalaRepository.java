package org.alberto.cinema.repository;

import org.alberto.cinema.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	public Sala findSalaByNomeSala(String nome);

}
