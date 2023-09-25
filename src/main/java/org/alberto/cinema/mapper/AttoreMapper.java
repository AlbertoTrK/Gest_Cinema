package org.alberto.cinema.mapper;

import java.util.ArrayList;
import java.util.List;

import org.alberto.cinema.dto.request.AttoreDTORequest;
import org.alberto.cinema.dto.response.AttoreDTOResponse;
import org.alberto.cinema.model.Attore;
import org.alberto.cinema.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttoreMapper {
	
	@Autowired
	private FilmMapper fMap;
	
	public AttoreDTOResponse toAttoreDTOResponse(Attore a) {
		AttoreDTOResponse aDTOResp = new AttoreDTOResponse();
		aDTOResp.setNome(a.getName());
		aDTOResp.setCognome(a.getSurname());
		List<Film> filmAttore = a.getFilmAttori();
		aDTOResp.setFilmDellAttore(fMap.toFilmDTOResponseList(filmAttore));
		return aDTOResp;
	}
	
	public List<AttoreDTOResponse> toAttoreDTOResponseList(List<Attore> attori){
		return attori.stream().map(this::toAttoreDTOResponse).toList();
	}
	
	//non usato
	public Attore toAttoreFromReqDTO(AttoreDTORequest req) {
		Attore a = new Attore();
		a.setName(req.getNome());
		a.setSurname(req.getCognome());
		a.setDeleted(req.getDeleted());
		List<Film> filmDiAttore = new ArrayList<>();
		a.setFilmAttori(filmDiAttore);
		return a;
	}
	
	//non usato
	public List<Attore> toAttoreFromReqDTOList(List<AttoreDTORequest> req){
		return req.stream().map(this::toAttoreFromReqDTO).toList();
	}

}
