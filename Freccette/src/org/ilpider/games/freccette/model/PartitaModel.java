package org.ilpider.games.freccette.model;

import java.util.LinkedList;
import java.util.List;

import org.ilpider.games.freccette.dao.GiocatoreDAO;

/**
 * Gestisce la logica del gioco. Prende e memorizza i dati chiamando i metodi
 * della classe {@link GiocatoreDAO}
 * 
 * @author Piero Sica
 *
 */
public class PartitaModel {

	private List<Giocatore> partita;

	/**
	 * Il costruttore crea tutte le volte una nuova lista di {@link Giocatore}
	 */
	public PartitaModel() {
		this.partita = new LinkedList<Giocatore>();
	}

	/**
	 * Costruttore con parametro per creare una nuova partita mantenendo la lista gia' usata per le partite precedenti
	 * 
	 * @param partita E' la lista di giocatorisu cui si basa la nuova partitia
	 */
	public PartitaModel(List<Giocatore> partita) {
		super();
		this.partita = partita;
	}


	/**
	 * 

	 * @param nome
	 */
	public void addGiocatore(String nome) {

		GiocatoreDAO dao = new GiocatoreDAO();

		if ( ! dao.esisteGiocatoreByNome(nome)) {
			// se il giocatore NON esiste
			System.out.println("non esiste");
			dao.addGiocatore(nome) ;
		}
		this.partita.add(dao.giocatoreByNome(nome)) ;
	}

	public int getNumeroGiocatori() {
		return this.partita.size();
	}

	public Giocatore vincitore() {
		// TODO andare a prendere il giocatore che ha il punteggio minore con
		// tutti i calcoli del caso...
		return null;
	}

	public int contaGiocatori() {
		GiocatoreDAO dao = new GiocatoreDAO();

		int numeroGiocatori = dao.contaTutti();

		return numeroGiocatori;
	}

	public List<Giocatore> listaGiocatori() {

		// GiocatoreDAO dao = new GiocatoreDAO() ;
		// this.partita = dao.listGiocatore() ;

		return this.partita;

	}
}
