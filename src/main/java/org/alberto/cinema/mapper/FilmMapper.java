package org.alberto.cinema.mapper;

import java.util.ArrayList;
import java.util.List;

import org.alberto.cinema.dto.response.FilmDTOResponse;
import org.alberto.cinema.model.Attore;
import org.alberto.cinema.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {
	
	public FilmDTOResponse toFilmDTOResponse(Film f) {
		FilmDTOResponse fDTOResp = new FilmDTOResponse();
		fDTOResp.setTitolo(f.getTitolo());
		fDTOResp.setDurata(f.getDurata());
		fDTOResp.setDescrizione(f.getDescrizione());
		fDTOResp.setNomeGenere(f.getGenere().getName());
		List<String> listaAttori = new ArrayList<>();
		for(Attore a : f.getAttoriFilm()) {
			String nomeAttore = a.getName() + " " + a.getSurname();
			listaAttori.add(nomeAttore);
		}
		fDTOResp.setAttoriDelFilm(listaAttori);
		return fDTOResp;
	}
	
	public List<FilmDTOResponse> toFilmDTOResponseList(List<Film> listaFilm){
		return listaFilm.stream().map(this::toFilmDTOResponse).toList();
	}

}
