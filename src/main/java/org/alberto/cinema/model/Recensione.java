package org.alberto.cinema.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Recensione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente", nullable = false)
	private Utente utente;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_film", nullable = false)
	private Film filmRecensito;
	@Column (nullable = false)
	private String testo;
	@Column (nullable = false)
	private int voto;
	@Column(nullable = false, columnDefinition = "BIT default 0")
	private Boolean deleted;
	
	public Recensione() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Film getFilmRecensito() {
		return filmRecensito;
	}

	public void setFilmRecensito(Film filmRecensito) {
		this.filmRecensito = filmRecensito;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}
}
