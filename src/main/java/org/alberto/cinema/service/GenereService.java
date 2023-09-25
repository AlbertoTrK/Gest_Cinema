package org.alberto.cinema.service;

import java.util.List;

import org.alberto.cinema.model.Genere;

public interface GenereService {
	public Genere findGenereByName(String name);
	public void save(Genere g);
	public Genere findById(long id);
	public List<Genere> findAll();
}
