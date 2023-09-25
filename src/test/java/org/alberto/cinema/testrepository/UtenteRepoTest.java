package org.alberto.cinema.testrepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.alberto.cinema.ProgettoCinemaApplication;
import org.alberto.cinema.model.Utente;
import org.alberto.cinema.repository.UtenteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ProgettoCinemaApplication.class)
public class UtenteRepoTest {
	
	@Autowired
	UtenteRepository uRepo;
	
	@Test
	public void login() {
		assertThat(uRepo.findUtenteByUsernameAndPassword("Dr_pacello97", "PaoloPac97_")).get().extracting(Utente::getNome).isEqualTo("Paolo");
	}
	
	@Test
	public void findUtenteByUsernameAndEmail() {
		assertThat(uRepo.findUtenteByUsernameAndEmail("Dr_pacello97", "pPacello@gmail.com")).get().extracting(Utente::getCognome).isEqualTo("Pacello");
	}
	
	@Test
	public void findById() {
		long idUtente = 1;
		List<Utente> utenti = uRepo.findAllById(idUtente);
		for(Utente u : utenti) {
			assertEquals(idUtente, u.getId());
		}
	}
	
	@Test
	public void findAll() {
		List<Utente> utenti = uRepo.findAll();
		assertThat(utenti).isNotNull();
		assertThat(utenti).isNotEmpty();
		int size = 5;
		assertThat(utenti).hasSize(size);
	}

}
