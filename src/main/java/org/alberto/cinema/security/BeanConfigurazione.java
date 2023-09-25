package org.alberto.cinema.security;

import org.alberto.cinema.exception.model.UtenteNonTrovatoException;
import org.alberto.cinema.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class BeanConfigurazione {
	
	@Autowired
	UtenteRepository uRepo;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return u->uRepo.findUtenteByUsername(u).orElseThrow(() -> new UtenteNonTrovatoException("nessun utente"));
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	public AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception{
		return conf.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAP = new DaoAuthenticationProvider();
		daoAP.setUserDetailsService(userDetailsService());
		daoAP.setPasswordEncoder(encoder());
		return daoAP;
	}

}
