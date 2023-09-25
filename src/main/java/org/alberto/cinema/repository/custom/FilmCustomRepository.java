package org.alberto.cinema.repository.custom;

import java.util.ArrayList;
import java.util.List;

import org.alberto.cinema.filtri.FiltraFilmRequest;
import org.alberto.cinema.model.Film;
import org.alberto.cinema.model.Genere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.alberto.cinema.model.Attore;

@Repository
public class FilmCustomRepository {
	
	@Autowired
	EntityManager manager;
	
	public List<Film> getFilmFiltrati(FiltraFilmRequest req){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
		Root<Film> root = query.from(Film.class);
		List<Predicate> pred = new ArrayList<>();
		if(req.getTitolo() != null) pred.add(builder.like(root.get("titolo"), "%"+req.getTitolo()+"%"));
		if(req.getDurata() != 0) pred.add(builder.like(builder.toString(root.get("durata")), "%"+req.getDurata()+"%"));
		if(req.getDescrizione() != null) pred.add(builder.like(root.get("descrizione"), "%"+req.getDescrizione()+"%"));
		if(req.getNomeGenere() != null) {
			Join<Film, Genere> joinGenere = root.join("genere");
			pred.add(builder.like(builder.lower(joinGenere.get("name")), "%"+req.getNomeGenere().toLowerCase()+"%"));
			query.multiselect(root, joinGenere);
		}
		if(req.getNomeAttore() != null) {
			Join<Film, Attore> joinNomeAtt = root.join("attoriFilm");
			pred.add(builder.like(builder.lower(joinNomeAtt.get("name")), "%"+req.getNomeAttore().toLowerCase()+"%"));
			query.multiselect(root, joinNomeAtt);
		}
		if(req.getCognomeAttore() != null) {
			Join<Film, Attore> joinCognomeAtt = root.join("attoriFilm");
			pred.add(builder.like(builder.lower(joinCognomeAtt.get("surname")), "%"+req.getCognomeAttore().toLowerCase()+"%"));
			query.multiselect(root, joinCognomeAtt);
		}
		Predicate [] arrPred = pred.toArray(new Predicate[pred.size()]);
		query.where(arrPred);
		List<Tuple> result = manager.createQuery(query).getResultList();
		return result.stream().map(t -> t.get(0, Film.class)).toList();
	}

}
