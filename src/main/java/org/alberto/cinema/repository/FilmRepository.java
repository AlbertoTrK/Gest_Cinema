package org.alberto.cinema.repository;

import org.alberto.cinema.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long>{
	public Film findByTitolo(String titolo);
}
