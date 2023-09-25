package org.alberto.cinema.service;

import java.util.List;

import org.alberto.cinema.model.Attore;

public interface AttoreService {
	public List<Attore> findAll();
	public Attore findById(long idAttore);
	public Attore findAttoreByNameAndSurname(String nome, String cognome);
	public void save(Attore a);
//	public String ritornaStringaCap(String s);
}
