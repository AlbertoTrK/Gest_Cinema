package org.alberto.cinema.mapper;

import java.util.List;

import org.alberto.cinema.dto.response.RecensioneDTOResponse;
import org.alberto.cinema.model.Recensione;
import org.springframework.stereotype.Component;

@Component
public class RecensioneMapper {
	
	public RecensioneDTOResponse toRecensioneDTOResponse(Recensione r) {
		RecensioneDTOResponse rDTOResp = new RecensioneDTOResponse();
		rDTOResp.setTestoRecensione(r.getTesto());
		rDTOResp.setTitoloFilm(r.getFilmRecensito().getTitolo());
		rDTOResp.setUsernameAutore(r.getUtente().getUsername());
		rDTOResp.setVoto(r.getVoto());
		return rDTOResp;
	}
	
	public List<RecensioneDTOResponse> toRecensioneDTOResponseList(List<Recensione> listaRecensioni) {
		return listaRecensioni.stream().map(this::toRecensioneDTOResponse).toList();
	}

}
