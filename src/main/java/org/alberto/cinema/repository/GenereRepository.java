package org.alberto.cinema.repository;

import java.util.List;
import java.util.Optional;

import org.alberto.cinema.model.Genere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenereRepository extends JpaRepository<Genere, Long>{
	public Optional<Genere> findGenereByName(String name);

	public List<Genere> findAllById(long idGenere); //per test repo

}
