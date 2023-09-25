package org.alberto.cinema.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Spettacolo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private LocalDateTime data;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Sala sala;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Film film;
	@OneToMany (mappedBy = "spettacolo",fetch = FetchType.LAZY)
	List<Biglietto> listaBiglietti;
	@Column(nullable = false, columnDefinition = "BIT default 0")
	private Boolean deleted;
	
	@Column(nullable = false)
	private double prezzo;
	
	public Spettacolo() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public List<Biglietto> getListaBiglietti() {
		return listaBiglietti;
	}

	public void setListaBiglietti(List<Biglietto> listaBiglietti) {
		this.listaBiglietti = listaBiglietti;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
}
