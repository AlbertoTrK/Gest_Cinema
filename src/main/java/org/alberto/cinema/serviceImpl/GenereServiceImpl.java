package org.alberto.cinema.serviceImpl;

import java.util.List;

import org.alberto.cinema.model.Genere;
import org.alberto.cinema.repository.GenereRepository;
import org.alberto.cinema.service.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenereServiceImpl implements GenereService{
	
	@Autowired
	private GenereRepository gRepo;

	@Override
	public Genere findGenereByName(String name) {
		return gRepo.findGenereByName(name).orElse(null);
	}

	@Override
	public void save(Genere g) {
		gRepo.save(g);
	}

	@Override
	public Genere findById(long id) {
		return gRepo.findById(id).orElse(null);
	}

	@Override
	public List<Genere> findAll() {
		return gRepo.findAll();
	}

}
