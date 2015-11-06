package org.ilpider.games.freccette.model;

import java.util.LinkedList;
import java.util.List;

import org.ilpider.games.freccette.dao.GiocatoreDAO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	 * @param partita E' la lista di giocatori su cui si basa la nuova partita
	 */
	public PartitaModel(List<Giocatore> partita) {
		super();
		this.partita = partita;
	}

	/**
	 * Aggiunge alla List<{@link Giocatore}> il Giocatore restituito dal {@link GiocatoreDAO}.
	 * Se il {@link Giocatore} NON e' presente nel database, lo aggiunge.
	 * 
	 * 2015-11-06 modificato il parametro da (String nome) a (List<String> elencoNomi)
	 * 
	 * @param nome del {@link Giocatore} da inserire
	 */
	public void inizializzaPartita(List<String> elencoNomi) {

		partita.removeAll(partita) ;

		GiocatoreDAO dao = new GiocatoreDAO();

		this.partita.addAll(dao.readGiocatoreByList(elencoNomi));
		// DEBUG Syso di controllo per verificare, grazie all'ID, se il giocatore inserito nella lista della partita viene letto dal database
		partita.forEach(g -> System.out.println("ID: " + g.getId() + " nome: " + g.getNome()));
		creaLayout(partita);
	}

	/**
	 * Genera il layout dei giocatori
	 */
	public void creaLayout(List<Giocatore> partita) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewGiocatore.fxml"));
			GridPane root1 = loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1,800,640));  
            stage.show();
		} catch (Exception e) {
			System.out.println("qualcosa non va");
			e.printStackTrace();

		}
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
