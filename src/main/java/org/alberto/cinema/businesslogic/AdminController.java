package org.alberto.cinema.businesslogic;

import java.util.List;

import org.alberto.cinema.dto.request.AttoreDTORequest;
import org.alberto.cinema.dto.request.FilmDTORequest;
import org.alberto.cinema.dto.request.GenereDTO;
import org.alberto.cinema.dto.request.RegistraUtenteDTO;
import org.alberto.cinema.dto.request.SalaDTORequest;
import org.alberto.cinema.dto.request.SpettacoloDTORequest;
import org.alberto.cinema.dto.response.AttoreDTOResponse;
import org.alberto.cinema.dto.response.FilmDTOResponse;
import org.alberto.cinema.dto.response.RecensioneDTOResponse;
import org.alberto.cinema.dto.response.SalaDTOResponse;
import org.alberto.cinema.dto.response.SpettacoloDTOResponse;
import org.alberto.cinema.dto.response.UtenteDTOResp;
import org.alberto.cinema.facade.AdminFacade;
import org.alberto.cinema.filtri.FiltraUtentiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminFacade aFac;
	
	@PostMapping("/cancellaUtente/{idUtente}")
	public ResponseEntity<String> cambiaStatoDeleted(@PathVariable long idUtente){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.cambiaStatoDeleted(idUtente));
	}
	
	@PostMapping("/inserisciGenere")
	public ResponseEntity<GenereDTO> inserisciGenere(@Valid @RequestBody GenereDTO request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.inserisciGenere(request));
	}
	
	@PostMapping("/modificaGenere/{idGenere}")
	public ResponseEntity<GenereDTO> modificaSala(@Valid @RequestBody GenereDTO request, @PathVariable long idGenere){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.modificaGenere(request, idGenere));
	}
	
	@PostMapping("/creaUtenteStaff")
	public ResponseEntity<UtenteDTOResp> creaUtenteStaff(@Valid @RequestBody RegistraUtenteDTO request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.creaUtenteStaff(request));
	}
	
	@PostMapping("/creaSala")
	public ResponseEntity<SalaDTOResponse> creaSala(@Valid @RequestBody SalaDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.creaSala(request));
	}
	
	@PostMapping("/modificaSala/{idSala}")
	public ResponseEntity<SalaDTOResponse> modificaSala(@Valid @RequestBody SalaDTORequest request, @PathVariable long idSala){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.modificaSala(request, idSala));
	}
	
	@PostMapping("/staff/creaSpettacolo")
	public ResponseEntity<SpettacoloDTOResponse> creaSpettacolo(/*@PathVariable long idUtente,*/ @Valid @RequestBody SpettacoloDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.creaSpettacolo(/*idUtente,*/ request));
	}
	
	@PostMapping("/staff/modificaSpettacolo/{idSpettacolo}")
	public ResponseEntity<SpettacoloDTOResponse> modificaSpettacolo(@Valid @RequestBody SpettacoloDTORequest request, @PathVariable long idSpettacolo){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.modificaSpettacolo(request, idSpettacolo));
	}
	
	@PostMapping("/inserisciAttore")
	public ResponseEntity<AttoreDTOResponse> inserisciAttore(@Valid @RequestBody AttoreDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.inserisciAttore(request));
	}
	
	@PostMapping("/modificaAttore/{idAttore}")
	public ResponseEntity<AttoreDTOResponse> modificaAttore(@Valid @RequestBody AttoreDTORequest request, @PathVariable long idAttore){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.modificaAttore(request, idAttore));
	}
	
	@PostMapping("/inserisciFilm")
	public ResponseEntity<FilmDTOResponse> inserisciFilm(@Valid @RequestBody FilmDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.inserisciFilm(request));
	}
	
	@PostMapping("/modificaFilm/{idFilm}")
	public ResponseEntity<FilmDTOResponse> modificaFilm(@Valid @RequestBody FilmDTORequest request, @PathVariable long idFilm){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.modificaFilm(request, idFilm));
	}
	
	@PostMapping("/filtraUtenti")
	public ResponseEntity<List<UtenteDTOResp>> filtraUtenti(@RequestBody FiltraUtentiRequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.filtraUtenti(request));
	}
	
	@GetMapping("/generiPerIncasso") //h
	public ResponseEntity<List<String>> generiPerIncasso(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.generiPerIncasso());
	}
	
	@GetMapping("/salePerIncasso")
	public ResponseEntity<List<String>> salePerIncasso(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.salePerIncasso());
	}
	
	@GetMapping("/clientiPiùFedeli")
	public ResponseEntity<List<String>> clientiPiùFedeli(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aFac.clientiPiùFedeli());
	}

}
