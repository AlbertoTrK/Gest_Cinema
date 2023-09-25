package org.alberto.cinema.serviceImpl;

import java.util.List;

import org.alberto.cinema.model.Recensione;
import org.alberto.cinema.repository.RecensioneRepository;
import org.alberto.cinema.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecensioneServiceImpl implements RecensioneService{
	
	@Autowired
	private RecensioneRepository rRepo;
	


	@Override
	public List<Recensione> findAll() {
		return rRepo.findAll();
	}


	@Override
	public void save(Recensione r) {
		rRepo.save(r);
	}

//	@Override
//	public Recensione findRecensioneByFilm_IdAndUtente_Id(long idFilm, long idUtente) {
//		return rRepo.findRecensioneByFilmIdAndUtenteId(idFilm, idUtente);
//	}

}
