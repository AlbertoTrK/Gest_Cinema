package org.alberto.cinema.security;

import java.io.IOException;

import org.alberto.cinema.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterAutenticazione extends OncePerRequestFilter{
	
	@Autowired
	GestoreToken gestoreToken;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String tokenJWT = authHeader.substring(7);
			if(gestoreToken.tokenAncoraValido(tokenJWT) && SecurityContextHolder.getContext().getAuthentication() == null) {
				Utente u = gestoreToken.getUtenteDaToken(tokenJWT);
				System.out.println("Ruolo utente: " + u.getAuthorities());
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(u,  null, u.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		filterChain.doFilter(request, response);
	}

}
