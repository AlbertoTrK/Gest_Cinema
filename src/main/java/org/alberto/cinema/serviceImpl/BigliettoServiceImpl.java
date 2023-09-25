package org.alberto.cinema.serviceImpl;

import java.util.List;

import org.alberto.cinema.model.Biglietto;
import org.alberto.cinema.repository.BigliettoRepository;
import org.alberto.cinema.service.BigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BigliettoServiceImpl implements BigliettoService{
	
	@Autowired
	private BigliettoRepository bRepo;

	@Override
	public void save(Biglietto b) {
		bRepo.save(b);
		
	}

	@Override
	public List<Biglietto> findAll() {
		return bRepo.findAll();
	}

}
