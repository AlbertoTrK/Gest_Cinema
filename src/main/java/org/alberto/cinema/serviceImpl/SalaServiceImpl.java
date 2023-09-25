package org.alberto.cinema.serviceImpl;

import org.alberto.cinema.model.Sala;
import org.alberto.cinema.repository.SalaRepository;
import org.alberto.cinema.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaServiceImpl implements SalaService{
	
	@Autowired
	private SalaRepository sRepo;

	@Override
	public Sala findSalaByNomeSala(String nome) {
		return sRepo.findSalaByNomeSala(nome);
	}

	@Override
	public void save(Sala s) {
		sRepo.save(s);
		
	}

	@Override
	public Sala findById(long idSala) {
		return sRepo.findById(idSala).orElse(null);
	}

}
