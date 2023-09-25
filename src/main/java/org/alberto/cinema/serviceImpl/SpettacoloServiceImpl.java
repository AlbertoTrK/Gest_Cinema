package org.alberto.cinema.serviceImpl;

import java.util.List;

import org.alberto.cinema.model.Spettacolo;
import org.alberto.cinema.repository.SpettacoloRepository;
import org.alberto.cinema.service.SpettacoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpettacoloServiceImpl implements SpettacoloService {
	
	@Autowired
	private SpettacoloRepository spRepo;

	@Override
	public Spettacolo findById(long id) {
		return spRepo.findById(id).orElse(null);
	}

	@Override
	public void save(Spettacolo sp) {
		spRepo.save(sp);
	}

	@Override
	public List<Spettacolo> findAll() {
		return spRepo.findAll();
	}

}
