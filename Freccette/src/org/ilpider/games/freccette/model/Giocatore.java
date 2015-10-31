package org.ilpider.games.freccette.model;

/**
 * Bean per la gestione dei dati dei giocatori
 * 
 * @author Piero Sica
 *
 */
public class Giocatore {

	private int id;
	private String nome;
	private int punti;

	public Giocatore(int id, String nome) {
		super();
		this.setId(id);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
