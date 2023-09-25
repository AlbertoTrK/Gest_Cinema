package org.alberto.cinema.testrepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.alberto.cinema.ProgettoCinemaApplication;
import org.alberto.cinema.model.Genere;
import org.alberto.cinema.repository.GenereRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ProgettoCinemaApplication.class)
public class GenereRepoTest {
	
	@Autowired
	GenereRepository gRepo;
	
	@Test
	public void findGenereByName() {
		assertThat(gRepo.findGenereByName("Horror")).get().extracting(Genere::getName).isEqualTo("Horror");
	}
	
	@Test
	public void findAll() {
		List<Genere> generi = gRepo.findAll();
		assertThat(generi).isNotNull();
		assertThat(generi).isNotEmpty();
		int size = 5;
		assertThat(generi).hasSize(size);
	}
	
	@Test
	public void save() {
		Genere g = new Genere();
		g.setDeleted(false);
		g.setName("Musical");
		gRepo.save(g);
		long idGenere = g.getId();
		Genere genInserito = gRepo.findById(idGenere).orElse(null);
		assertThat(genInserito).isNotNull();
		assertThat(genInserito.getDeleted()).isEqualTo(false);
		assertThat(genInserito.getName()).isEqualTo("Musical");
	}
	
	@Test
	public void findById() {
		long idGenere = 1;
		List<Genere> generi = gRepo.findAllById(idGenere);
		for(Genere g : generi) {
			assertEquals(idGenere, g.getId());
		}
		
	}

}
