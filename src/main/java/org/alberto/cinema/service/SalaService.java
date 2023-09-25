package org.alberto.cinema.service;

import org.alberto.cinema.model.Sala;

public interface SalaService {
	public Sala findSalaByNomeSala(String nome);
	public void save(Sala s);
	public Sala findById(long idSala);

}
