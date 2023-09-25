package org.alberto.cinema.facade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.alberto.cinema.businesslogic.utilities.Utilities;
import org.alberto.cinema.dto.request.LoginDTORequest;
import org.alberto.cinema.dto.request.RecensioneDTORequest;
import org.alberto.cinema.dto.request.RegistraUtenteDTO;
import org.alberto.cinema.dto.response.AttoreDTOResponse;
import org.alberto.cinema.dto.response.BigliettoDTOResponse;
import org.alberto.cinema.dto.response.FilmDTOResponse;
import org.alberto.cinema.dto.response.LoginDTOResponse;
import org.alberto.cinema.dto.response.RecensioneDTOResponse;
import org.alberto.cinema.dto.response.SpettacoloDTOResponse;
import org.alberto.cinema.dto.response.UtenteDTOResp;
import org.alberto.cinema.filtri.FiltraFilmRequest;
import org.alberto.cinema.mapper.AttoreMapper;
import org.alberto.cinema.mapper.BigliettoMapper;
import org.alberto.cinema.mapper.FilmMapper;
import org.alberto.cinema.mapper.GenereMapper;
import org.alberto.cinema.mapper.RecensioneMapper;
import org.alberto.cinema.mapper.SpettacoloMapper;
import org.alberto.cinema.mapper.UtenteMapper;
import org.alberto.cinema.model.Attore;
import org.alberto.cinema.model.Biglietto;
import org.alberto.cinema.model.Film;
import org.alberto.cinema.model.Genere;
import org.alberto.cinema.model.Recensione;
import org.alberto.cinema.model.Ruolo;
import org.alberto.cinema.model.Spettacolo;
import org.alberto.cinema.model.Utente;
import org.alberto.cinema.service.AttoreService;
import org.alberto.cinema.service.BigliettoService;
import org.alberto.cinema.service.FilmService;
import org.alberto.cinema.service.GenereService;
import org.alberto.cinema.service.RecensioneService;
import org.alberto.cinema.service.SpettacoloService;
import org.alberto.cinema.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UtenteFacade {
	
	@Autowired
	private UtenteService uServ;
	
	@Autowired
	private UtenteMapper uMap;
	
	@Autowired
	private FilmService fServ;
	
	@Autowired
	private FilmMapper fMap;
	
	@Autowired
	private GenereService gServ;
	
	@Autowired
	private GenereMapper gMap;
	
	@Autowired
	private AttoreService aServ;
	
	@Autowired
	private AttoreMapper aMap;
	
	@Autowired
	private SpettacoloService spServ;
	
	@Autowired
	private SpettacoloMapper spMap;
	
	@Autowired
	private BigliettoService bServ;
	
	@Autowired
	private BigliettoMapper bMap;
	
	@Autowired
	private RecensioneService rServ;
	
	@Autowired
	private RecensioneMapper rMap;
	
	public UtenteDTOResp registrazione(RegistraUtenteDTO req) {
		
		/*
		Utente u = uServ.findUtenteByUsername(req.getUsername());
		Utente u2 = uServ.findUtenteByEmail(req.getEmail());
		
		if(u != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username già esistente");
		}
		else if(u2 != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email già esistente");
		}
		
		*/
		
		boolean utenteEsistente = uServ.verificaUsernameEEmail(req.getUsername(), req.getEmail());
		
		if(utenteEsistente == true) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email o username già esistente");
		}
		else {
			Utente ut = new Utente();
			ut.setNome(Utilities.rendiInizMaiuscERestoMin(req.getNome()));
			ut.setCognome(Utilities.rendiInizMaiuscERestoMin(req.getCognome()));
			ut.setEmail(req.getEmail());
			ut.setUsername(req.getUsername());
			ut.setPassword(req.getPassword());
			ut.setDeleted(false);
			ut.setRuolo(Ruolo.UTENTE);
			uServ.registra(ut); 
			UtenteDTOResp uDTOResp = uMap.toUtenteDTOResp(ut);
			return uDTOResp;
		}
	}
	
	public LoginDTOResponse login(LoginDTORequest request) {
		Utente u = uServ.login(request.getUsername(), request.getPassword());
		
		if(u == null || u.getDeleted() == true) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "credenziali errate");
		}
		else {
			LoginDTOResponse lDTO = uMap.toLoginDTOResp(u);
			return lDTO;
		}
	}
	
	public Utente loginToken(LoginDTORequest request) {
		Utente u = uServ.login(request.getUsername(), request.getPassword());
		
		if(u == null || u.getDeleted() == true) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "credenziali errate");
		}
		else {
			return u;
		}
	}
	
	public List<FilmDTOResponse> vediTuttiIFilm() {
		List<Film> tuttiIFilm = fServ.findAll().stream().filter(f -> f.getDeleted() == false).toList();
		if(tuttiIFilm.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "nessun film presente");
		}
		else {
			List<FilmDTOResponse> listaFilm = fMap.toFilmDTOResponseList(tuttiIFilm);
			return listaFilm;
		}
		
	}
	
	public List<FilmDTOResponse> vediFilmDiGenere(String nome){
		Genere g = gServ.findGenereByName(Utilities.rendiInizMaiuscERestoMin(nome));
		
		if(g == null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "genere inesistente");
		}
		else {
			List<Film> filmDelGenere = fServ.findAll().stream().filter(f -> f.getDeleted() == false).filter(f -> f.getGenere().getName().equalsIgnoreCase(nome)).toList();
			if(filmDelGenere.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "nessun film di questo genere presente");
			}
			else {
				List<FilmDTOResponse> listaFilm = fMap.toFilmDTOResponseList(filmDelGenere);
				return listaFilm;
			}
		}
		
	}
	
	// tutti gli attori e i film in cui sono presenti
	public List<AttoreDTOResponse> listaAttoriELoroFilm(){
		List<Attore> listaAttori = aServ.findAll().stream().filter(a -> a.getDeleted() == false).toList();
		
		if(listaAttori.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "nessun attore presente in database");
		}
		else {
			List<AttoreDTOResponse> aDTORespList = aMap.toAttoreDTOResponseList(listaAttori);
			return aDTORespList;
		}
	}
	
	// attore e i film in cui è presente
	public AttoreDTOResponse attoreESuoiFilm(long idAttore){
		Attore attore = aServ.findById(idAttore);
		
		if(attore == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "attore non trovato");
		}
		else {
			AttoreDTOResponse aDTOResp = aMap.toAttoreDTOResponse(attore);
			if(aDTOResp.getFilmDellAttore().isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "nessun film dell'attore presente");
			}
			else {
				return aDTOResp;
			}
			
		}
	}
	
	//acquista biglietto
	public BigliettoDTOResponse acquistaBiglietto(long idUtente, long idSpettacolo) {
		Spettacolo sp = spServ.findById(idSpettacolo);
		Utente u = uServ.findById(idUtente);
		List<Biglietto> tuttiIBigliettiDiSpettacolo = bServ.findAll().stream().filter(b -> b.getSpettacolo() == sp).toList();
		
		if(sp == null || u == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "spettacolo o utente non trovato");
		}
		else {
			// verificare che ci siano ancora biglietto disponibili 
			if(tuttiIBigliettiDiSpettacolo.size() < sp.getSala().getCapienza()) {
				Biglietto b = new Biglietto();
				b.setSpettacolo(sp);
				b.setUtente(u);
				b.setDeleted(false);
				b.setPrezzo(sp.getPrezzo());
				bServ.save(b);
				BigliettoDTOResponse bDTOResp = bMap.toBigliettoDTOResponse(b);
				return bDTOResp;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "biglietti esauriti, capienza sala raggiunta");
			}
			
		}
	}
	
	public List<FilmDTOResponse> filtraFilm(FiltraFilmRequest req){
		if(fServ.getFilmFiltrati(req).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nessun film trovato con questi criteri");
		}
		else {
			List<Film> listaFilmFiltrati = fServ.getFilmFiltrati(req).stream().filter(f -> f.getDeleted() == false).toList();
			return fMap.toFilmDTOResponseList(listaFilmFiltrati);
		}
	}
	
	//Visualizzare tutti gli spettacoli in proiezione per quel giorno
	public List<SpettacoloDTOResponse> spettacoliOdierni(){
		LocalDate now = LocalDate.now();
		List<Spettacolo> spettacoliDiOggi = spServ.findAll().stream().filter(sp -> sp.getData().toLocalDate().isEqual(now))
				.filter(sp -> sp.getDeleted() == false)
				.toList();
		if(spettacoliDiOggi.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "nessuno spettacolo in programmazione oggi");
		}
		else {
			return spMap.toSpettDTOResponseList(spettacoliDiOggi);
		}
	}
	
	public List<SpettacoloDTOResponse> vediSpettacoli(){
		List<Spettacolo> spettacoli = spServ.findAll().stream().filter(sp -> sp.getDeleted() == false)
				.filter(sp -> sp.getData().isAfter(LocalDateTime.now()))
				.toList();
		
		if(spettacoli.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "nessuno spettacolo in programmazione");
		}
		else {
			return spMap.toSpettDTOResponseList(spettacoli);
		}
	}
	
	//visualizzare lo storico degli spettacoli goduti
	public List<BigliettoDTOResponse> listaSpettacoliGoduti(long idUtente){
		Utente u = uServ.findById(idUtente);
		
		if(u == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "utente non trovato");
		}
		else {
			List<BigliettoDTOResponse> spettacoliUtente = bMap.toBigliettoDTOResponseList(u.getBiglietti());
			return spettacoliUtente;
		}
	}
	
	//lasciare una recensione per il film
	public RecensioneDTOResponse inserisciRecensione(RecensioneDTORequest req) {
		Utente u = uServ.findById(req.getIdUtente());
		Film f = fServ.findById(req.getIdFilm());
		List<Recensione> listaRece = rServ.findAll().stream().filter(r -> r.getUtente().getId() == req.getIdUtente())
				.filter(r -> r.getFilmRecensito().getId() == req.getIdFilm()).toList();
		
		if(u == null || f == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "utente o film non trovato");
		}
		else if(u.getRuolo() == Ruolo.ADMIN || u.getRuolo() == Ruolo.STAFF) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "utenti admin o staff non possono inserire recensioni");
		}
		else {
			Recensione r = new Recensione();
			r.setTesto(req.getTesto());
			r.setFilmRecensito(f);
			r.setUtente(u);
			r.setDeleted(false);
			r.setVoto(req.getVoto());
			if(listaRece.isEmpty()) {
				rServ.save(r);
				RecensioneDTOResponse rDTOResp = rMap.toRecensioneDTOResponse(r);
				return rDTOResp;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "recensione per questo film già inserita dall'utente");
			}
			
		}
	}
	
	public List<RecensioneDTOResponse> vediTutteRecensioni(){
		List<Recensione> tutteRecensioni = rServ.findAll().stream().filter(r -> r.getDeleted() == false).toList();
		
		if(tutteRecensioni.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nessuna recensione in database");
		}
		else {
			return rMap.toRecensioneDTOResponseList(tutteRecensioni);
		}
	}
	
	public List<RecensioneDTOResponse> vediRecensioniFilm(String titolo){
		Film f = fServ.findByTitolo(titolo);
		if(f == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nessun film con questo titolo in database");
		}
		
		List<Recensione> tutteRecensioni = rServ.findAll().stream().filter(r -> r.getDeleted() == false)
				.filter(r -> r.getFilmRecensito().getTitolo().equals(titolo))
				.toList();
		
		if(tutteRecensioni.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nessuna recensione in database per questo film");
		}
		else {
			return rMap.toRecensioneDTOResponseList(tutteRecensioni);
		}
	}
	
	public double vediMediaVotoFilm(String titolo) {
		Film f = fServ.findByTitolo(titolo);
		if(f == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nessun film con questo titolo in database");
		}
		
		List<Recensione> tutteRecensioni = rServ.findAll().stream().filter(r -> r.getDeleted() == false)
				.filter(r -> r.getFilmRecensito().getTitolo().equals(titolo))
				.toList();
		
		if(tutteRecensioni.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nessuna recensione in database per questo film");
		}
		else {
			double totale = 0;
			
			for(Recensione r : tutteRecensioni) {
				totale += r.getVoto();
			}
			
			double media = totale / tutteRecensioni.size();
			
			//double media = tutteRecensioni.stream().mapToDouble(Recensione::getVoto).average().orElse(0.0);
			
			return media;
		}
	}
}
