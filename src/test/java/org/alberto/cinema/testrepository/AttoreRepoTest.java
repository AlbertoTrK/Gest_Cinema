package org.alberto.cinema.testrepository;

import org.alberto.cinema.model.Film;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.alberto.cinema.ProgettoCinemaApplication;
import org.alberto.cinema.model.Attore;
import org.alberto.cinema.repository.AttoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ProgettoCinemaApplication.class)
public class AttoreRepoTest {
	
	@Autowired
	AttoreRepository aRepo;
	
	@Test
	public void findAll(){
		List<Attore> attori = aRepo.findAll();
		assertThat(attori).isNotEmpty();
		assertThat(attori).isNotNull();
		int size = 7;
		assertThat(attori).hasSize(size);
	}
	
	@Test
	public void findById() {
		long idAttore = 1;
		List<Attore> attori = aRepo.findAllById(idAttore);
		for(Attore a : attori) {
			assertEquals(idAttore, a.getId());
		}
	}
	
	@Test
	public void findAttoreByNameAndSurname() {
		assertThat(aRepo.findAttoreByNameAndSurname("Brad", "Pitt")).extracting(Attore::getId).isEqualTo(1L);
	}
	
	@Test
	public void save() {
		Attore a = new Attore();
		a.setName("Paolo");
		a.setSurname("Kessisoglu");
		a.setDeleted(false);
		List<Film> filmDiAttore = new ArrayList<>();
		a.setFilmAttori(filmDiAttore);
		aRepo.save(a);
		long idAttore = a.getId();
		Attore attInserito = aRepo.findById(idAttore).orElse(null);
		assertThat(attInserito).isNotNull();
		assertThat(attInserito.getName()).isEqualTo("Paolo");
		assertThat(attInserito.getSurname()).isEqualTo("Kessisoglu");
		assertThat(attInserito.getDeleted()).isFalse();
//		assertThat(attInserito.getFilmAttori()).isEqualTo(filmDiAttore); //da lazy exception se decommentata
	}
	
	
}
