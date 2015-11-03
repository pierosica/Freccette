package org.ilpider.games.freccette.model;

import org.ilpider.games.freccette.dao.GiocatoreDAO;

/**
 * E' la classe in cui metto tutti i metodi che mi servono per gestire l'entita' {@link Giocatore} e che chiamera' i metodi presenti in {@link GiocatoreDAO}
 * 
 * @author Piero Sica
 *
 */
public class GiocatoreModel {

	/**
	 * 
	 * Controlla se nel database esiste gia' un {@link Giocatore} che ha nome uguale a quello passato come parametro
	 * 
	 * @param nome
	 *            e' il nome del {@link Giocatore} che devo cercare nel database
	 * 
	 * @return TRUE se il nome è stato trovato. FALSE se non viene trovato
	 * 
	 */
	public boolean esisteGiocatoreNelDB(String nome) {

		GiocatoreDAO dao = new GiocatoreDAO();

		if (!dao.esisteGiocatoreByNome(nome)) {
			// se il giocatore NON esiste
			dao.addGiocatore(nome);
		}

		return false;
	}
}
