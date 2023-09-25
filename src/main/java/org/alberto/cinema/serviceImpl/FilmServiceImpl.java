package org.alberto.cinema.serviceImpl;

import java.util.List;

import org.alberto.cinema.filtri.FiltraFilmRequest;
import org.alberto.cinema.model.Film;
import org.alberto.cinema.repository.FilmRepository;
import org.alberto.cinema.repository.custom.FilmCustomRepository;
import org.alberto.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService{
	
	@Autowired
	private FilmRepository fRepo;
	
	@Autowired
	private FilmCustomRepository fCustRepo;

	@Override
	public List<Film> findAll() {
		return fRepo.findAll();
	}

	@Override
	public Film findById(long id) {
		return fRepo.findById(id).orElse(null);
	}

	@Override
	public Film findByTitolo(String titolo) {
		return fRepo.findByTitolo(titolo);
	}

	@Override
	public void save(Film f) {
		fRepo.save(f);
	}

	@Override
	public List<Film> getFilmFiltrati(FiltraFilmRequest req) {
		return fCustRepo.getFilmFiltrati(req);
	}

}
