package org.alberto.cinema.repository;

import org.alberto.cinema.model.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecensioneRepository extends JpaRepository<Recensione, Long>{
//	public Recensione findRecensioneByFilmIdAndUtenteId(long idFilm, long idUtente);
}
