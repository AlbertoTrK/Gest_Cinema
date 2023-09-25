package org.alberto.cinema.testcontroller;

import org.alberto.cinema.ProgettoCinemaApplication;
import org.alberto.cinema.dto.request.LoginDTORequest;
import org.alberto.cinema.dto.request.RegistraUtenteDTO;
import org.alberto.cinema.model.Ruolo;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@ContextConfiguration(classes = ProgettoCinemaApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class AllControllerTest {
	
	private final WebApplicationContext wac;
	private MockMvc mock;
	
	private AllControllerTest (WebApplicationContext w) {
		wac = w;
	}
	
	@BeforeEach
	public void creaMock() {
		mock = MockMvcBuilders.webAppContextSetup(wac).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}
	
	@Test @Order(1)
	void registrazione() throws Exception{
		RegistraUtenteDTO ruDTO = new RegistraUtenteDTO();
		ruDTO.setNome("Ciro");
		ruDTO.setCognome("Gennari");
		ruDTO.setUsername("Ciruzzo99_");
		ruDTO.setPassword("Password123!_");
		ruDTO.setEmail("ciruzzoG@gmail.com");
		ruDTO.setRuolo(Ruolo.UTENTE);
		String json = new ObjectMapper().writeValueAsString(ruDTO);
		mock.perform(MockMvcRequestBuilders.post("/all/registrazione").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @Order(2)
	void registrazioneUtenteEsistente() throws Exception{
		RegistraUtenteDTO ruDTO = new RegistraUtenteDTO();
		ruDTO.setNome("Paolo");
		ruDTO.setCognome("Pacello");
		ruDTO.setUsername("Dr_pacello97");
		ruDTO.setPassword("PaoloPac97_");
		ruDTO.setEmail("pPacello@gmail.com");
		ruDTO.setRuolo(Ruolo.UTENTE);
		String json = new ObjectMapper().writeValueAsString(ruDTO);
		mock.perform(MockMvcRequestBuilders.post("/all/registrazione").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test @Order(3)
	void registrazioneNonValida() throws Exception{
		RegistraUtenteDTO ruDTO = new RegistraUtenteDTO();
		ruDTO.setNome("Luca");
		ruDTO.setCognome("Rossi");
		ruDTO.setUsername("lRossi_00");
		ruDTO.setPassword("lRossi");
		ruDTO.setEmail("lRossi@gmail.com");
		ruDTO.setRuolo(Ruolo.UTENTE);
		String json = new ObjectMapper().writeValueAsString(ruDTO);
		mock.perform(MockMvcRequestBuilders.post("/all/registrazione").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}
	
	@Test @Order(4)
	void loginOk() throws Exception {
		LoginDTORequest lDTO = new LoginDTORequest();
		lDTO.setUsername("Dr_pacello97");
		lDTO.setPassword("PaoloPac97_");
		String json = new ObjectMapper().writeValueAsString(lDTO);
		mock.perform(MockMvcRequestBuilders.post("/all/loginToken").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
	}
	
	@Test @Order(5)
	void loginUserNotFound() throws Exception{
		LoginDTORequest lDTO = new LoginDTORequest();
		lDTO.setUsername("Ciro99");
		lDTO.setPassword("ciroEsposito99_");
		String json = new ObjectMapper().writeValueAsString(lDTO);
		mock.perform(MockMvcRequestBuilders.post("/all/loginToken").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
	}

}
