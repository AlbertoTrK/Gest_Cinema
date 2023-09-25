package org.alberto.cinema.businesslogic;

import java.util.List;

import org.alberto.cinema.dto.request.RecensioneDTORequest;
import org.alberto.cinema.dto.response.AttoreDTOResponse;
import org.alberto.cinema.dto.response.BigliettoDTOResponse;
import org.alberto.cinema.dto.response.FilmDTOResponse;
import org.alberto.cinema.dto.response.RecensioneDTOResponse;
import org.alberto.cinema.dto.response.SpettacoloDTOResponse;
import org.alberto.cinema.facade.UtenteFacade;
import org.alberto.cinema.filtri.FiltraFilmRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/utente")
public class UtenteController {
	
	@Autowired
	private UtenteFacade uFac;
	
	@GetMapping("/vediTuttiIFilm")
	public ResponseEntity<List<FilmDTOResponse>> vediTuttiIFilm(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.vediTuttiIFilm());
	}
	
	@GetMapping("/filmDiUnGenere/{nomeGenere}")
	public ResponseEntity<List<FilmDTOResponse>> vediFilmDiUnGenere(@PathVariable String nomeGenere){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.vediFilmDiGenere(nomeGenere));
	}
	
	@GetMapping("/listaAttoriELoroFilm")
	public ResponseEntity<List<AttoreDTOResponse>> listaAttoriELoroFilm(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.listaAttoriELoroFilm());
	}
	
	@GetMapping("/attoreESuoiFilm/{idAttore}")
	public ResponseEntity<AttoreDTOResponse> attoreESuoiFilm(@PathVariable long idAttore){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.attoreESuoiFilm(idAttore));
	}
	
	@PostMapping("/compraBiglietto/{idUtente}/{idSpettacolo}")
	public ResponseEntity<BigliettoDTOResponse> compraBiglietto(@PathVariable long idUtente, @PathVariable long idSpettacolo){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.acquistaBiglietto(idUtente, idSpettacolo));
	}
	
	@PostMapping("/filtraFilm")
	public ResponseEntity<List<FilmDTOResponse>> filtraFilm(@RequestBody FiltraFilmRequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.filtraFilm(request));
	}
	
	@GetMapping("/vediSpettacoliOdierni")
	public ResponseEntity<List<SpettacoloDTOResponse>> vediSpettacoliOdierni(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.spettacoliOdierni());
	}
	
	@GetMapping("/vediProgrammazione")
	public ResponseEntity<List<SpettacoloDTOResponse>> vediProgrammazione(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.vediSpettacoli());
	}
	
	@GetMapping("/listaSpettacoliGoduti/{idUtente}")
	public ResponseEntity<List<BigliettoDTOResponse>> spettacoliGoduti(@PathVariable long idUtente){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.listaSpettacoliGoduti(idUtente));
	}
	
	@PostMapping("/inserisciRecensione")
	public ResponseEntity<RecensioneDTOResponse> inserisciRecensione(@Valid @RequestBody RecensioneDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.inserisciRecensione(request));
	}
	
	@GetMapping("/vediTutteRecensioni")
	public ResponseEntity<List<RecensioneDTOResponse>> vediTutteRecensioni(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.vediTutteRecensioni());
	}
	
	@GetMapping("/vediTutteRecensioniFilm/{titolo}")
	public ResponseEntity<List<RecensioneDTOResponse>> vediTutteRecensioniFilm(@PathVariable String titolo){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.vediRecensioniFilm(titolo));
	}
	
	@GetMapping("/vediMediaVotoFilm/{titolo}")
	public ResponseEntity<Double> vediMediaVotoFilm(@PathVariable String titolo){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uFac.vediMediaVotoFilm(titolo));
	}

}
