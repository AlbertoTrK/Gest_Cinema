package org.alberto.cinema.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column (nullable = false)
	private String titolo;
	@Column (nullable = false)
	private int durata;
	@Column (nullable = false)
	private String descrizione;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "film_attori", joinColumns = @JoinColumn(name = "id_film"), inverseJoinColumns = @JoinColumn(name = "id_attore"))
	private List <Attore> attoriFilm;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Genere genere;
	@Column(nullable = false, columnDefinition = "BIT default 0")
	private Boolean deleted;
	@OneToMany(mappedBy = "filmRecensito")
	private List<Recensione> recensioniFilm;
	
	
	
	public Film() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Attore> getAttoriFilm() {
		return attoriFilm;
	}

	public void setAttoriFilm(List<Attore> attoriFilm) {
		this.attoriFilm = attoriFilm;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}
