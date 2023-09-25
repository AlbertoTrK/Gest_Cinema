package org.alberto.cinema.service;
import java.util.List;

import org.alberto.cinema.filtri.FiltraFilmRequest;
import org.alberto.cinema.model.Film;

public interface FilmService {
	public List<Film> findAll();
	public Film findById(long id);
	public Film findByTitolo(String titolo);
	public void save(Film f);
	public List<Film> getFilmFiltrati(FiltraFilmRequest req);
}
