package org.alberto.cinema.service;

import java.util.List;

import org.alberto.cinema.model.Spettacolo;

public interface SpettacoloService {
	public Spettacolo findById(long id);
	public void save(Spettacolo sp);
	public List<Spettacolo> findAll();
}
