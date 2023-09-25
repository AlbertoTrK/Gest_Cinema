package org.alberto.cinema.serviceImpl;

import java.util.List;

import org.alberto.cinema.model.Attore;
import org.alberto.cinema.repository.AttoreRepository;
import org.alberto.cinema.service.AttoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttoreServiceImpl implements AttoreService{
	
	@Autowired
	private AttoreRepository aRepo;

	@Override
	public List<Attore> findAll() {
		return aRepo.findAll();
	}

	@Override
	public Attore findById(long idAttore) {
		return aRepo.findById(idAttore).orElse(null);
	}

	@Override
	public Attore findAttoreByNameAndSurname(String nome, String cognome) {
		return aRepo.findAttoreByNameAndSurname(nome, cognome);
	}

	@Override
	public void save(Attore a) {
		aRepo.save(a);
	}

//	@Override
//	public String ritornaStringaCap(String s) {
//		String inizialeStrMaiusc = s.substring(0, 1).toUpperCase();
//		String restoDellaStr = s.substring(1);
//		String stringaInizialeMaiusc = inizialeStrMaiusc + restoDellaStr;
//		return stringaInizialeMaiusc;
//	}

}
