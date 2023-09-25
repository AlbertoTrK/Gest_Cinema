package org.alberto.cinema.testcontroller;

import org.alberto.cinema.ProgettoCinemaApplication;
import org.alberto.cinema.dto.request.RecensioneDTORequest;
import org.alberto.cinema.filtri.FiltraFilmRequest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
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

@SpringBootTest
@ContextConfiguration(classes = ProgettoCinemaApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class UtenteControllerTest {
	
	private final WebApplicationContext wac;
	private MockMvc mock;
	
	private UtenteControllerTest (WebApplicationContext w) {
		wac = w;
	}
	
	@BeforeEach
	public void creaMock() {
		mock = MockMvcBuilders.webAppContextSetup(wac).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void vediTuttiIFilm() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/utente/vediTuttiIFilm").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE")
	void verificaRisultatoListaFilm() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/utente/vediTuttiIFilm").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].titolo").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].titolo").value("Barbie"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].durata").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].durata").value(90))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].descrizione").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].descrizione").value("Film basato sul franchise Barbie"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nomeGenere").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nomeGenere").value("Commedia"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].attoriDelFilm[0]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].attoriDelFilm[0]").value("Margot Robbie"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].attoriDelFilm[1]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].attoriDelFilm[1]").value("Ryan Gosling"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].titolo").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].titolo").value("Once Upon A Time In Hollywood"))
		//ecc ecc
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void vediFilmDiGenere() throws Exception{
		String nomeGenere = "Drammatico";
		mock.perform(MockMvcRequestBuilders.get("/utente/filmDiUnGenere/{nomeGenere}", nomeGenere).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void vediFilmDiGenereNonEsistente() throws Exception{
		String nomeGenere = "Sportivo";
		mock.perform(MockMvcRequestBuilders.get("/utente/filmDiUnGenere/{nomeGenere}", nomeGenere).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void listaAttoriELoroFilm() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/utente/listaAttoriELoroFilm").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE")
	void verificalistaAttoriELoroFilm() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/utente/listaAttoriELoroFilm").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Brad"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].cognome").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].cognome").value("Pitt"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].titolo").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].titolo").value("Once Upon A Time In Hollywood"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].durata").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].durata").value(150))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].descrizione").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].descrizione").value("Film ambientato negli anni 60 di Quentin Tarantino"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].nomeGenere").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].nomeGenere").value("Thriller"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].attoriDelFilm[0]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].attoriDelFilm[0]").value("Brad Pitt"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].attoriDelFilm[1]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].attoriDelFilm[1]").value("Leonardo DiCaprio"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].attoriDelFilm[2]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[0].attoriDelFilm[2]").value("Margot Robbie"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].titolo").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].titolo").value("Sleepers"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].durata").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].durata").value(90))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].descrizione").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].descrizione").value(""))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].nomeGenere").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].nomeGenere").value("Drammatico"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].attoriDelFilm[0]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].attoriDelFilm[0]").value("Brad Pitt"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].attoriDelFilm[1]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].filmDellAttore[1].attoriDelFilm[1]").value("Robert DeNiro"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Leonardo"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].cognome").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].cognome").value("DiCaprio"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].titolo").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].titolo").value("Once Upon A Time In Hollywood"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].durata").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].durata").value(150))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].descrizione").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].descrizione").value("Film ambientato negli anni 60 di Quentin Tarantino"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].nomeGenere").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].nomeGenere").value("Thriller"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].attoriDelFilm[0]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].attoriDelFilm[0]").value("Brad Pitt"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].attoriDelFilm[1]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].attoriDelFilm[1]").value("Leonardo DiCaprio"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].attoriDelFilm[2]").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].filmDellAttore[0].attoriDelFilm[2]").value("Margot Robbie"))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void attoreESuoiFilm() throws Exception{
		long idAttore = 1;
		mock.perform(MockMvcRequestBuilders.get("/utente/attoreESuoiFilm/{idAttore}", idAttore).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void attoreESuoiFilmNull() throws Exception{
		long idAttore = 0;
		mock.perform(MockMvcRequestBuilders.get("/utente/attoreESuoiFilm/{idAttore}", idAttore).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void attoreESuoiFilmNoContent() throws Exception{
		long idAttore = 4;
		mock.perform(MockMvcRequestBuilders.get("/utente/attoreESuoiFilm/{idAttore}", idAttore).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void acquistaBiglietto() throws Exception{
		long idUtente = 1;
		long idSpettacolo = 1;
		
		mock.perform(MockMvcRequestBuilders.post("/utente/compraBiglietto/{idUtente}/{idSpettacolo}", idUtente, idSpettacolo).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void filtraFilm() throws Exception{
		FiltraFilmRequest ffReq = new FiltraFilmRequest();
		ffReq.setNomeGenere("Drammatico");
		
		String json = new ObjectMapper().writeValueAsString(ffReq);
		mock.perform(MockMvcRequestBuilders.post("/utente/filtraFilm").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(username = "Dr_pacello97", roles = "UTENTE")
	void listaSpettacoliGoduti() throws Exception{
		long idUtente = 1;
		
		mock.perform(MockMvcRequestBuilders.get("/utente/listaSpettacoliGoduti/{idUtente}", idUtente).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE") @Order(1)
	void inserisciRecensione() throws Exception {
		RecensioneDTORequest rDTOReq = new RecensioneDTORequest();
		rDTOReq.setIdFilm(1);
		rDTOReq.setIdUtente(1);
		rDTOReq.setVoto(2);
		rDTOReq.setTesto("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String json = new ObjectMapper().writeValueAsString(rDTOReq);
		
		mock.perform(MockMvcRequestBuilders.post("/utente/inserisciRecensione").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE") @Order(2)
	void inserisciRecensioneNonAccettabile() throws Exception {
		RecensioneDTORequest rDTOReq = new RecensioneDTORequest();
		rDTOReq.setIdFilm(1);
		rDTOReq.setIdUtente(1);
		rDTOReq.setVoto(2);
		rDTOReq.setTesto("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String json = new ObjectMapper().writeValueAsString(rDTOReq);
		
		mock.perform(MockMvcRequestBuilders.post("/utente/inserisciRecensione").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isNotAcceptable()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE") @Order(2)
	void inserisciRecensioneFilmOUtenteNonTrovato() throws Exception {
		RecensioneDTORequest rDTOReq = new RecensioneDTORequest();
		rDTOReq.setIdFilm(111);
		rDTOReq.setIdUtente(1);
		rDTOReq.setTesto("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String json = new ObjectMapper().writeValueAsString(rDTOReq);
		
		mock.perform(MockMvcRequestBuilders.post("/utente/inserisciRecensione").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE") @Order(3)
	void vediTutteRecensioni() throws Exception{
		mock.perform(MockMvcRequestBuilders.get("/utente/vediTutteRecensioni").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE") @Order(3)
	void vediTutteRecensioniFilm() throws Exception{
		String titolo = "Barbie";
		mock.perform(MockMvcRequestBuilders.get("/utente/vediTutteRecensioniFilm/{titolo}", titolo).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @WithMockUser(roles = "UTENTE") @Order(3)
	void vediMediaVotoFilm() throws Exception{
		String titolo = "Barbie";
		mock.perform(MockMvcRequestBuilders.get("/utente/vediMediaVotoFilm/{titolo}", titolo).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}

}
