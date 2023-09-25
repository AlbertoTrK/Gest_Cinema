package org.alberto.cinema.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;

import org.alberto.cinema.businesslogic.utilities.Utilities;
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
import org.alberto.cinema.filtri.FiltraUtentiRequest;
import org.alberto.cinema.mapper.AttoreMapper;
import org.alberto.cinema.mapper.FilmMapper;
import org.alberto.cinema.mapper.GenereMapper;
import org.alberto.cinema.mapper.RecensioneMapper;
import org.alberto.cinema.mapper.SalaMapper;
import org.alberto.cinema.mapper.SpettacoloMapper;
import org.alberto.cinema.mapper.UtenteMapper;
import org.alberto.cinema.model.Attore;
import org.alberto.cinema.model.Biglietto;
import org.alberto.cinema.model.Film;
import org.alberto.cinema.model.Genere;
import org.alberto.cinema.model.Recensione;
import org.alberto.cinema.model.Ruolo;
import org.alberto.cinema.model.Sala;
import org.alberto.cinema.model.Spettacolo;
import org.alberto.cinema.model.Utente;
import org.alberto.cinema.service.AttoreService;
import org.alberto.cinema.service.FilmService;
import org.alberto.cinema.service.GenereService;
import org.alberto.cinema.service.RecensioneService;
import org.alberto.cinema.service.SalaService;
import org.alberto.cinema.service.SpettacoloService;
import org.alberto.cinema.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminFacade {
	
	@Autowired
	private UtenteService uServ;
	
	@Autowired
	private GenereService gServ;
	
	@Autowired
	private GenereMapper gMap;
	
	@Autowired
	private UtenteMapper uMap;
	
	@Autowired
	private SalaService sServ;
	
	@Autowired
	private SalaMapper sMap;
	
	@Autowired
	private SpettacoloService spServ;
	
	@Autowired
	private SpettacoloMapper spMap;
	
	@Autowired
	private FilmService fServ;
	
	@Autowired
	private FilmMapper fMap;
	
	@Autowired
	private AttoreService aServ;
	
	@Autowired
	private AttoreMapper aMap;
	
	@Autowired
	private RecensioneService rServ;
	
	@Autowired
	private RecensioneMapper rMap;
	
	public String cambiaStatoDeleted(long idUtente) {
		Utente u = uServ.findById(idUtente);
		
		if(u == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "utente inesistente");
		}
		else if(u.getDeleted() == true) {
			u.setDeleted(false);
			uServ.update(u);
			return "Lo stato cancellato dell'utente " + u.getUsername() + " è stato settato su false";
		}
		else {
			u.setDeleted(true);
			uServ.update(u);
			return "Lo stato cancellato dell'utente " + u.getUsername() + " è stato settato su true";
		}
	}
	
	public GenereDTO inserisciGenere(GenereDTO request) {
		String nomeGen = Utilities.rendiInizMaiuscERestoMin(request.getName());
		Genere g = gServ.findGenereByName(nomeGen);
		if(g != null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "genere con questo nome già esistente");
		}
		else {
			g = new Genere();
			g.setName(nomeGen);
			g.setDeleted(request.getDeleted());
			gServ.save(g);
			return gMap.toGenereDTO(g);
		}
	}
	
	//con annotazione
//	public GenereDTO inserisciGenere(GenereDTO request) {
//		Genere g = gServ.findGenereByName(request.getName());
//		if(g != null) {
//			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "genere con questo nome già esistente");
//		}
//		else {
//			g = new Genere();
//			g.setName(request.getName());
//			g.setDeleted(request.getDeleted());
//			gServ.save(g);
//			return gMap.toGenereDTO(g);
//		}
//	}
	
	public GenereDTO modificaGenere(GenereDTO req, long idGenere) {
		Genere g = gServ.findById(idGenere);
		
		if(g == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "genere non trovato");
		}
		else {
			g.setDeleted(req.getDeleted());
			g.setName(Utilities.rendiInizMaiuscERestoMin(req.getName()));
			Genere gTest = gServ.findGenereByName(Utilities.rendiInizMaiuscERestoMin(req.getName()));
			if(gTest == null) {
				gServ.save(g);
				GenereDTO gDTO = gMap.toGenereDTO(g);
				return gDTO;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "genere con questo nome già esistente, riprovare");
			}
		}
	}
	
	public UtenteDTOResp creaUtenteStaff(RegistraUtenteDTO req) {
		boolean utenteEsistente = uServ.verificaUsernameEEmail(req.getUsername(), req.getEmail());
		
		if(utenteEsistente == true) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email o username già esistente");
		}
		else {
			Utente ut = new Utente();
			ut.setNome(req.getNome());
			ut.setCognome(req.getCognome());
			ut.setEmail(req.getEmail());
			ut.setUsername(req.getUsername());
			ut.setPassword(req.getPassword());
			ut.setDeleted(false);
			ut.setRuolo(Ruolo.STAFF);
			uServ.registra(ut);
			UtenteDTOResp uDTOResp = uMap.toUtenteDTOResp(ut);
			return uDTOResp;
		}
	}
	
	public SalaDTOResponse creaSala(SalaDTORequest req) {
		Sala s = sServ.findSalaByNomeSala(req.getNomeSala());
		
		if(s == null) {
			Sala s2 = new Sala();
			s2.setNomeSala(req.getNomeSala());
			s2.setCapienza(req.getCapienza());
			s2.setDeleted(req.getDeleted());
			sServ.save(s2);
			SalaDTOResponse sDTOResp = sMap.toSalaDTOResponse(s2);
			return sDTOResp;
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sala con questo nome già esistente");
		}
		
	}
	
	public SalaDTOResponse modificaSala(SalaDTORequest req, long idSala) {
		Sala s = sServ.findById(idSala);
		
		if(s == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "sala non trovata");
		}
		else {
			s.setCapienza(req.getCapienza());
			s.setDeleted(req.getDeleted());
			s.setNomeSala(req.getNomeSala());
			Sala sTest = sServ.findSalaByNomeSala(req.getNomeSala());
			if (sTest == null) {
				sServ.save(s);
				SalaDTOResponse sDTOResp = sMap.toSalaDTOResponse(s);
				return sDTOResp;
			} 
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sala con questo nome già esistente");
			}

		}
	}
	
	public SpettacoloDTOResponse creaSpettacolo(/*long idUtente, */SpettacoloDTORequest req) {
		Film filmInsert = fServ.findById(req.getIdFilm());
		Sala salaInsert = sServ.findById(req.getIdSala());
		//Utente u = uServ.findById(idUtente);
		List<Spettacolo> allSpett = spServ.findAll();
		
		if(filmInsert == null || salaInsert == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sala o film non trovato");
		}
		/*
		else if(u.getRuolo() != Ruolo.ADMIN || u.getRuolo() != Ruolo.STAFF) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "autorizzazione negata");
		}*/
		else {
			Spettacolo sp = new Spettacolo();
			sp.setData(req.getData());
			sp.setFilm(filmInsert);
			sp.setSala(salaInsert);
			sp.setPrezzo(req.getPrezzo());
			sp.setDeleted(req.isDeleted());
			for(Spettacolo spett : allSpett) {
				if(spett.getData().isEqual(sp.getData()) && spett.getSala().getId() == sp.getSala().getId()) {
					throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "sala già occupata a quell'ora");
				}
			}
			spServ.save(sp);
			SpettacoloDTOResponse spDTOResp = spMap.toSpettDTOResponse(sp);
			return spDTOResp;
		}
	}
	
	public SpettacoloDTOResponse modificaSpettacolo(SpettacoloDTORequest req, long idSpettacolo) {
		Film filmInsert = fServ.findById(req.getIdFilm());
		Sala salaInsert = sServ.findById(req.getIdSala());
		List<Spettacolo> allSpett = spServ.findAll();
		Spettacolo sp = spServ.findById(idSpettacolo);
		List<Biglietto> bigliettiSpettacolo = spServ.findAll().stream().filter(spett -> spett.getId() == idSpettacolo).map(Spettacolo::getListaBiglietti).findFirst().orElse(Collections.emptyList());
		
		if(filmInsert == null || salaInsert == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sala o film per modifica non trovato");
		}
		else if(sp == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "spettacolo da modificare non trovato");
		}
		else {
			sp.setData(req.getData());
			sp.setDeleted(req.isDeleted());
			sp.setFilm(filmInsert);
			sp.setSala(salaInsert);
			sp.setPrezzo(req.getPrezzo());
			sp.setListaBiglietti(bigliettiSpettacolo);
			for(Spettacolo spett : allSpett) {
				if(spett.getId() != idSpettacolo && spett.getData().isEqual(req.getData()) && spett.getSala().getId() == req.getIdSala()) {
					throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "sala già occupata a quell'ora");
				}
			}
			spServ.save(sp);
			SpettacoloDTOResponse spDTOResp = spMap.toSpettDTOResponse(sp);
			return spDTOResp;
		}
		
	}
	
	public AttoreDTOResponse inserisciAttore(AttoreDTORequest req){
		Attore a = aServ.findAttoreByNameAndSurname(Utilities.rendiInizMaiuscERestoMin(req.getNome()), Utilities.rendiInizMaiuscERestoMin(req.getCognome()));
		
		if(a != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attore già presente in database");
		}
		else {
			Attore attInsert = new Attore();
			attInsert.setName(Utilities.rendiInizMaiuscERestoMin(req.getNome()));
			attInsert.setSurname(Utilities.rendiInizMaiuscERestoMin(req.getCognome()));
			attInsert.setDeleted(req.getDeleted());
			List<Film> filmDiAttore = new ArrayList<>(); //possibilità di inserire attore E film suoi già presenti in db? (cambiando dto)
			attInsert.setFilmAttori(filmDiAttore);
			aServ.save(attInsert);
			AttoreDTOResponse aDTOResp = aMap.toAttoreDTOResponse(attInsert);
			return aDTOResp;
		}
		
	}
	
	public AttoreDTOResponse modificaAttore(AttoreDTORequest req, long idAttore) {
		Attore a = aServ.findById(idAttore);
		
		if(a == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attore non presente in database");
		}
		else {
			a.setDeleted(req.getDeleted());
			a.setName(Utilities.rendiInizMaiuscERestoMin(req.getNome()));
			a.setSurname(Utilities.rendiInizMaiuscERestoMin(req.getCognome()));
			a.setFilmAttori(a.getFilmAttori());
			Attore atTest = aServ.findAttoreByNameAndSurname(Utilities.rendiInizMaiuscERestoMin(req.getNome()), Utilities.rendiInizMaiuscERestoMin(req.getCognome()));
			if(atTest == null) {
				aServ.save(a);
				AttoreDTOResponse aDTOResp = aMap.toAttoreDTOResponse(a);
				return aDTOResp;
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attore con questo nome e cognome già presente in database");
			}
		}
	}
	
	public FilmDTOResponse inserisciFilm(FilmDTORequest req) {
		Film f = fServ.findByTitolo(req.getTitolo());
		Genere g = gServ.findById(req.getIdGenere());
		if(f != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Film con titolo " + req.getTitolo() + " già presente in database");
		}
		else if(g == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Genere con id " + req.getIdGenere() + " non trovato in database");
		}
		else {
			Film filmInsert = new Film();
			filmInsert.setTitolo(req.getTitolo());
			filmInsert.setDurata(req.getDurata());
			filmInsert.setDescrizione(req.getDescrizione());
			filmInsert.setDeleted(req.isDeleted());
			filmInsert.setGenere(g);
			List<Long> idAttori = req.getAttoriDelFilm();
			List<Attore> attoriDelFilm = new ArrayList<>();
			for (Long l : idAttori) {
				Attore a = aServ.findById(l);
				if (a != null) {
					attoriDelFilm.add(a);
				}
				else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attore con id " + l +  " non trovato in database");
				}
			}
			//filmInsert.setAttoriFilm(aMap.toAttoreFromReqDTOList(req.getAttoriDelFilm()));
			filmInsert.setAttoriFilm(attoriDelFilm);
			fServ.save(filmInsert);
			
			FilmDTOResponse fDTOResponse = fMap.toFilmDTOResponse(filmInsert);
			return fDTOResponse;
		}
	}
	
	public FilmDTOResponse modificaFilm(FilmDTORequest req, long idFilm) {
		Film f = fServ.findById(idFilm);
		Genere g = gServ.findById(req.getIdGenere());
		
		if(f == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trovato");
		}
		else if(g == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genere non trovato");
		}
		else {
			f.setDeleted(req.isDeleted());
			f.setDescrizione(req.getDescrizione());
			f.setDurata(req.getDurata());
			f.setGenere(g);
			f.setTitolo(req.getTitolo());
			List<Long> idAttoriFilm = req.getAttoriDelFilm();
			List<Attore> attoriFilm = idAttoriFilm.stream().map(aServ::findById).peek(attore -> {
				if(attore == null) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attore non trovato in database");
				}
			}).collect(Collectors.toList());
			f.setAttoriFilm(attoriFilm);
			Film fTest = fServ.findByTitolo(req.getTitolo());
			if(fTest == null) {
				fServ.save(f);
				FilmDTOResponse fDTOResp = fMap.toFilmDTOResponse(f);
				return fDTOResp;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Film con questo titolo già esistente");
			}
		}
	}
	
	public List<UtenteDTOResp> filtraUtenti(FiltraUtentiRequest req){
		if(uServ.getUtentiFiltrati(req).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nessun utente trovato con questi criteri");
		}
		else {
			return uMap.toListUtenteDTOResp(uServ.getUtentiFiltrati(req));
		}
	}
	
	//generi più redditizi
	public List<String> generiPerIncasso(){ //h
		List<Spettacolo> listaSpettacoli = spServ.findAll().stream().filter(sp -> sp.getDeleted() == false).toList();
		List<String> listaIncassi = new ArrayList<>();
		if(listaSpettacoli.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nessuno spettacolo in database");
		}
		else {
			Map<String, Double> incassiPerGenere = new HashMap<>();
			for(Spettacolo sp : listaSpettacoli) {
				String nomeGenere = sp.getFilm().getGenere().getName();
				double incassi = incassiPerGenere.getOrDefault(nomeGenere, 0.0);
				incassi += sp.getPrezzo() * (sp.getListaBiglietti().size() + 1);;
				incassiPerGenere.put(nomeGenere, incassi);
			}
			List<Map.Entry<String, Double>> sortedIncassi = new ArrayList<>(incassiPerGenere.entrySet());
			sortedIncassi.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));
			
			for (Map.Entry<String, Double> entry : sortedIncassi) {
	            listaIncassi.add("Il genere " + entry.getKey() + " ha incassato " + entry.getValue());
	        }
		}
		
		return listaIncassi;
	}
	
	//sale piu' redditizie
	public List<String> salePerIncasso(){
		List<Spettacolo> listaSpettacoli = spServ.findAll().stream().filter(sp -> sp.getDeleted() == false).toList();
		List<String> listaIncassi = new ArrayList<>();
		if(listaSpettacoli.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nessun genere in database");
		}
		else {
			Map<String, Double> incassiPerSala = new HashMap<>();
			for(Spettacolo sp : listaSpettacoli) {
				String nomeSala = sp.getSala().getNomeSala();
				double incassi = incassiPerSala.getOrDefault(nomeSala, 0.0);
				incassi += sp.getPrezzo() * (sp.getListaBiglietti().size() + 1);
				incassiPerSala.put(nomeSala, incassi);
			}
			List<Map.Entry<String, Double>> sortedIncassi = new ArrayList<>(incassiPerSala.entrySet());
			sortedIncassi.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));
			
			for (Map.Entry<String, Double> entry : sortedIncassi) {
	            listaIncassi.add("La sala " + entry.getKey() + " ha incassato " + entry.getValue());
	        }
		}
		return listaIncassi;
	}
	
	//clienti piu' fedeli
	public List<String> clientiPiùFedeli(){
		List<Utente> utenti = uServ.findAll().stream().filter(u -> u.getDeleted() == false).filter(u -> u.getRuolo() == Ruolo.UTENTE).toList();
		List<String> listaAcquisti = new ArrayList<>();
		if(utenti.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nessun utente in database");
		}
		else {
			Map<String, Integer> listaUtentiPerBiglietti = new HashMap<>();
			for(Utente u : utenti) {
				String nomeUtente = u.getUsername();
				int counterBiglietti = listaUtentiPerBiglietti.getOrDefault(nomeUtente, 0);
				counterBiglietti += u.getBiglietti().size();
				listaUtentiPerBiglietti.put(nomeUtente, counterBiglietti);
			}
			List<Map.Entry<String, Integer>> sortedCounter = new ArrayList<>(listaUtentiPerBiglietti.entrySet());
			sortedCounter.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));
			
			for (Map.Entry<String, Integer> entry : sortedCounter) {
				listaAcquisti.add("L'utente " + entry.getKey() + " ha comprato " + entry.getValue() + " biglietti");
	        }
		}
		return listaAcquisti;
	}

}
