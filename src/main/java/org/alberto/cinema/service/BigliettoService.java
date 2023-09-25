package org.alberto.cinema.service;

import java.util.List;

import org.alberto.cinema.model.Biglietto;

public interface BigliettoService {
	public void save(Biglietto b);
	public List<Biglietto> findAll();
}
