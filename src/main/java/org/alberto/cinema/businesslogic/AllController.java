package org.alberto.cinema.businesslogic;

import org.alberto.cinema.dto.request.LoginDTORequest;
import org.alberto.cinema.dto.request.RegistraUtenteDTO;
import org.alberto.cinema.dto.response.LoginDTOResponse;
import org.alberto.cinema.dto.response.UtenteDTOResp;
import org.alberto.cinema.facade.UtenteFacade;
import org.alberto.cinema.mapper.UtenteMapper;
import org.alberto.cinema.model.Utente;
import org.alberto.cinema.security.GestoreToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/all")
public class AllController {
	
	@Autowired
	private UtenteFacade uFac;
	
	@Autowired
	private GestoreToken gesTok;
	
	@Autowired
	private UtenteMapper uMap;
	
	@PostMapping("/registrazione")
	public ResponseEntity<UtenteDTOResp> registrazione(@Valid @RequestBody RegistraUtenteDTO request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.registrazione(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTOResponse> login(@Valid @RequestBody LoginDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.login(request));
	}
	
	@PostMapping("/loginToken")
	public ResponseEntity<LoginDTOResponse> loginToken(@Valid @RequestBody LoginDTORequest request){
		Utente u = uFac.loginToken(request);
		String token = gesTok.creaToken(u);
		System.out.println("Ruolo utente: " + u.getAuthorities());
		System.out.println("Richiesta loginToken ricevuta.");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Authorization",  token).body(uMap.toLoginDTOResp(u));
	}

}
