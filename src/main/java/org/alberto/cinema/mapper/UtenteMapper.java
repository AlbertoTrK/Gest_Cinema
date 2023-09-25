package org.alberto.cinema.mapper;

import java.util.List;

import org.alberto.cinema.dto.response.LoginDTOResponse;
import org.alberto.cinema.dto.response.UtenteDTOResp;
import org.alberto.cinema.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {
	
	public LoginDTOResponse toLoginDTOResp (Utente u) {
		LoginDTOResponse lDTO = new LoginDTOResponse();
		lDTO.setUsername(u.getUsername());
		lDTO.setNome(u.getNome());
		lDTO.setCognome(u.getCognome());
		return lDTO;
	}
	
	public UtenteDTOResp toUtenteDTOResp (Utente u) {
		UtenteDTOResp uDTOResp = new UtenteDTOResp();
		uDTOResp.setUsername(u.getUsername());
		uDTOResp.setNome(u.getNome());
		uDTOResp.setCognome(u.getCognome());
		uDTOResp.setRuolo(u.getRuolo());
		uDTOResp.setDeleted(u.getDeleted());
		return uDTOResp;
	}
	
	public List<UtenteDTOResp> toListUtenteDTOResp (List<Utente> listaUtenti){
		return listaUtenti.stream().map(this::toUtenteDTOResp).toList();
	}

}
