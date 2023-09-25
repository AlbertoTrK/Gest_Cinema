package org.alberto.cinema.mapper;

import java.util.List;

import org.alberto.cinema.dto.response.BigliettoDTOResponse;
import org.alberto.cinema.dto.response.LoginDTOResponse;
import org.alberto.cinema.dto.response.SpettacoloDTOResponse;
import org.alberto.cinema.model.Biglietto;
import org.alberto.cinema.model.Spettacolo;
import org.alberto.cinema.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BigliettoMapper {
	
	@Autowired
	private UtenteMapper uMap;
	
	@Autowired
	private SpettacoloMapper spMap;
	
	public BigliettoDTOResponse toBigliettoDTOResponse (Biglietto b) {
		BigliettoDTOResponse bDTOResponse = new BigliettoDTOResponse();
		Utente u = b.getUtente();
		Spettacolo sp = b.getSpettacolo();
		
		LoginDTOResponse lDTOResp = uMap.toLoginDTOResp(u);
		SpettacoloDTOResponse spDTOResp = spMap.toSpettDTOResponse(sp);
		bDTOResponse.setlDTOResp(lDTOResp);
		bDTOResponse.setSpDTOResp(spDTOResp);
		return bDTOResponse;
	}
	
	public List<BigliettoDTOResponse> toBigliettoDTOResponseList(List<Biglietto> biglietti){
		return biglietti.stream().map(this::toBigliettoDTOResponse).toList();
	}

}
