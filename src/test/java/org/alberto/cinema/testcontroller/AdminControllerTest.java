package org.alberto.cinema.testcontroller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.alberto.cinema.ProgettoCinemaApplication;
import org.alberto.cinema.dto.request.AttoreDTORequest;
import org.alberto.cinema.dto.request.FilmDTORequest;
import org.alberto.cinema.dto.request.GenereDTO;
import org.alberto.cinema.dto.request.RegistraUtenteDTO;
import org.alberto.cinema.dto.request.SalaDTORequest;
import org.alberto.cinema.dto.request.SpettacoloDTORequest;
import org.alberto.cinema.dto.response.AttoreDTOResponse;
import org.alberto.cinema.dto.response.FilmDTOResponse;
import org.alberto.cinema.dto.response.UtenteDTOResp;
import org.alberto.cinema.filtri.FiltraUtentiRequest;
import org.alberto.cinema.model.Ruolo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@ContextConfiguration(classes = ProgettoCinemaApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class AdminControllerTest {
	
	private final WebApplicationContext wac;
	private MockMvc mock;
	
	private AdminControllerTest (WebApplicationContext w) {
		wac = w;
	}
	
	@BeforeEach
	public void creaMock() {
		mock = MockMvcBuilders.webAppContextSetup(wac).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void cambiaStatoDeleted() throws Exception{
		long idUtente = 1;
		
		mock.perform(MockMvcRequestBuilders.post("/admin/cancellaUtente/{idUtente}", idUtente).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void cambiaStatoDeletedNotFound() throws Exception{
		long idUtente = 900;
		
		mock.perform(MockMvcRequestBuilders.post("/admin/cancellaUtente/{idUtente}", idUtente).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN") @Order(1)
	void inserisciGenere() throws Exception{
		GenereDTO gDto = new GenereDTO();
		gDto.setName("Sportivo");
		gDto.setDeleted(false);
		
		String json = new ObjectMapper().writeValueAsString(gDto);
		mock.perform(MockMvcRequestBuilders.post("/admin/inserisciGenere").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void modificaGenere() throws Exception{
		GenereDTO gDto = new GenereDTO();
		gDto.setName("Musical");
		gDto.setDeleted(false);
		long idGenere = 1;
		String json = new ObjectMapper().writeValueAsString(gDto);
		
		mock.perform(MockMvcRequestBuilders.post("/admin/modificaGenere/{idGenere}", idGenere).contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void modificaGenereIdFail() throws Exception{
		GenereDTO gDto = new GenereDTO();
		gDto.setName("Horror");
		gDto.setDeleted(false);
		long idGenere = 11;
		String json = new ObjectMapper().writeValueAsString(gDto);
		
		mock.perform(MockMvcRequestBuilders.post("/admin/modificaGenere/{idGenere}", idGenere).contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN") @Order(2)
	void inserisciGenereGiàEsistente() throws Exception{
		GenereDTO gDto = new GenereDTO();
		gDto.setName("Sportivo");
		gDto.setDeleted(false);
		
		String json = new ObjectMapper().writeValueAsString(gDto);
		mock.perform(MockMvcRequestBuilders.post("/admin/inserisciGenere").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isNotAcceptable()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void creaUtenteStaff() throws Exception{
		RegistraUtenteDTO rDTOReq = new RegistraUtenteDTO();
		rDTOReq.setNome("Luca");
		rDTOReq.setCognome("Pazzini");
		rDTOReq.setUsername("lPazzini95!");
		rDTOReq.setPassword("pazzoPazzini_STAFF95!");
		rDTOReq.setEmail("pPazzini_staff@gmail.com");
		rDTOReq.setRuolo(Ruolo.STAFF);
		String json = new ObjectMapper().writeValueAsString(rDTOReq);
		
		mock.perform(MockMvcRequestBuilders.post("/admin/creaUtenteStaff").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN") @Order(1)
	void creaSala() throws Exception{
		SalaDTORequest sDTOReq = new SalaDTORequest();
		sDTOReq.setCapienza(180);
		sDTOReq.setDeleted(false);
		sDTOReq.setNomeSala("Sala test");
		
		String json = new ObjectMapper().writeValueAsString(sDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/creaSala").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN") @Order(2)
	void creaSalaFail() throws Exception{
		SalaDTORequest sDTOReq = new SalaDTORequest();
		sDTOReq.setCapienza(180);
		sDTOReq.setDeleted(false);
		sDTOReq.setNomeSala("Sala test");
		
		String json = new ObjectMapper().writeValueAsString(sDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/creaSala").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN") @Order(3)
	void modificaSala() throws Exception{
		SalaDTORequest sDTOReq = new SalaDTORequest();
		sDTOReq.setCapienza(180);
		sDTOReq.setDeleted(false);
		sDTOReq.setNomeSala("Sala test modifica");
		long idSala = 1;
		String json = new ObjectMapper().writeValueAsString(sDTOReq);
		
		mock.perform(MockMvcRequestBuilders.post("/admin/modificaSala/{idSala}", idSala).contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void creaSpettacolo() throws Exception{
		SpettacoloDTORequest spDTOReq = new SpettacoloDTORequest();
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//LocalDateTime data = LocalDateTime.parse("2023-09-06 23:00:00", formatter);
		//LocalDate data = new LocalDate(0, 0, 0);
		//LocalDateTime dataTime = new LocalDateTime(null, null);
		
		/*
		LocalDate data = LocalDate.of(2023, 9, 6);
	    LocalTime ora = LocalTime.of(23, 0);
	    //LocalDateTime dataTime = LocalDateTime.of(data, ora);
		spDTOReq.setData(LocalDateTime.of(data, ora));
		*/
		
		
	    String data2 = "2023-09-06T23:00:00";
	    LocalDateTime dataIns = LocalDateTime.parse(data2);
		spDTOReq.setData(dataIns);
		
		
		/*
		String dataString = "2023-09-06T23:00:00";
	    spDTOReq.setDataString(dataString);
		*/
		ObjectMapper mapper = JsonMapper.builder()
			    .addModule(new JavaTimeModule()) //permette inserimento data, senza dà errore
			    .build();
		
		spDTOReq.setDeleted(false);
		spDTOReq.setIdFilm(2);
		spDTOReq.setIdSala(2);
		spDTOReq.setPrezzo(9.70);
		String json = mapper.writeValueAsString(spDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/staff/creaSpettacolo").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void inserisciAttore() throws Exception{
		AttoreDTORequest aDTOReq = new AttoreDTORequest();
		aDTOReq.setNome("Luca");
		aDTOReq.setCognome("Marinelli");
		aDTOReq.setDeleted(false);
		String json = new ObjectMapper().writeValueAsString(aDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/inserisciAttore").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void modificaAttore() throws Exception{
		AttoreDTORequest aDTOReq = new AttoreDTORequest();
		aDTOReq.setNome("Al");
		aDTOReq.setCognome("Pacino");
		aDTOReq.setDeleted(false);
		long idAttore = 1;
		String json = new ObjectMapper().writeValueAsString(aDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/modificaAttore/{idAttore}", idAttore).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void inserisciAttoreGiàEsistente() throws Exception{
		AttoreDTORequest aDTOReq = new AttoreDTORequest();
		aDTOReq.setNome("Brad");
		aDTOReq.setCognome("Pitt");
		aDTOReq.setDeleted(false);
		String json = new ObjectMapper().writeValueAsString(aDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/inserisciAttore").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void inserisciFilm() throws Exception{
		FilmDTORequest fDTOReq = new FilmDTORequest();
		fDTOReq.setTitolo("Pirati dei Caraibi");
		fDTOReq.setDescrizione("Film sui pirati");
		fDTOReq.setDurata(120);
		fDTOReq.setIdGenere(5);
		fDTOReq.setDeleted(false);
		List<Long> idAttoriFilm = new ArrayList<>();
		idAttoriFilm.add((long) 1);
		idAttoriFilm.add((long) 2);
		fDTOReq.setAttoriDelFilm(idAttoriFilm);
		String json = new ObjectMapper().writeValueAsString(fDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/inserisciFilm").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void inserisciFilmGiàEsistente() throws Exception{
		FilmDTORequest fDTOReq = new FilmDTORequest();
		fDTOReq.setTitolo("Barbie");
		fDTOReq.setDescrizione("Film sul franchise di bambole");
		fDTOReq.setDurata(120);
		fDTOReq.setIdGenere(5);
		fDTOReq.setDeleted(false);
		List<Long> idAttoriFilm = new ArrayList<>();
		idAttoriFilm.add((long) 1);
		idAttoriFilm.add((long) 2);
		fDTOReq.setAttoriDelFilm(idAttoriFilm);
		String json = new ObjectMapper().writeValueAsString(fDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/inserisciFilm").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void modificaFilm() throws Exception{
		FilmDTORequest fDTOReq = new FilmDTORequest();
		fDTOReq.setTitolo("The Lobster");
		fDTOReq.setDescrizione("Thriller con Colin Farrell");
		fDTOReq.setDurata(120);
		fDTOReq.setIdGenere(3);
		fDTOReq.setDeleted(false);
		List<Long> idAttoriFilm = new ArrayList<>();
		idAttoriFilm.add((long) 1);
		idAttoriFilm.add((long) 2);
		fDTOReq.setAttoriDelFilm(idAttoriFilm);
		long idFilm = 4;
		String json = new ObjectMapper().writeValueAsString(fDTOReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/modificaFilm/{idFilm}", idFilm).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void filtraUtenti() throws Exception{
		FiltraUtentiRequest fUtReq = new FiltraUtentiRequest();
		fUtReq.setUsername("pac");
		String json = new ObjectMapper().writeValueAsString(fUtReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/filtraUtenti").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void filtraUtentiNoContent() throws Exception{
		FiltraUtentiRequest fUtReq = new FiltraUtentiRequest();
		fUtReq.setUsername("gok");
		String json = new ObjectMapper().writeValueAsString(fUtReq);
		mock.perform(MockMvcRequestBuilders.post("/admin/filtraUtenti").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void generiPerIncasso() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/admin/generiPerIncasso").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void salePerIncasso() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/admin/salePerIncasso").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "ADMIN")
	void clientiPiùFedeli() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/admin/clientiPiùFedeli").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}

}
