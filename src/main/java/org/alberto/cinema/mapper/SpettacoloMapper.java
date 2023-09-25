package org.alberto.cinema.mapper;

import java.util.List;

import org.alberto.cinema.dto.response.SpettacoloDTOResponse;
import org.alberto.cinema.model.Spettacolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpettacoloMapper {
	
	@Autowired
	private SalaMapper sMap;
	
	@Autowired
	private FilmMapper fMap;
	
	public SpettacoloDTOResponse toSpettDTOResponse(Spettacolo sp) {
		SpettacoloDTOResponse spDTOResp = new SpettacoloDTOResponse();
		spDTOResp.setData(sp.getData());
		spDTOResp.setPrezzo(sp.getPrezzo());
		spDTOResp.setSala(sMap.toSalaDTOResponse(sp.getSala()));
		spDTOResp.setFilm(fMap.toFilmDTOResponse(sp.getFilm()));
		return spDTOResp;
	}
	
	public List<SpettacoloDTOResponse> toSpettDTOResponseList(List<Spettacolo> spettacoli){
		return spettacoli.stream().map(this::toSpettDTOResponse).toList();
	}

}
