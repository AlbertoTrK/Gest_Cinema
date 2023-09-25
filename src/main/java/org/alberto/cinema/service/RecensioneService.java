package org.alberto.cinema.service;

import java.util.List;

import org.alberto.cinema.model.Recensione;

public interface RecensioneService {
	public void save(Recensione r);
//	public Recensione findRecensioneByFilm_IdAndUtente_Id(long idFilm, long idUtente);
	public List<Recensione> findAll();
}
