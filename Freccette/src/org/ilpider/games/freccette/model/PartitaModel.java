package org.ilpider.games.freccette.model;

import java.util.LinkedList;
import java.util.List;

import org.ilpider.games.freccette.dao.GiocatoreDAO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
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
	 * Aggiunge alla propria List<{@link Giocatore}> quella restituita dal {@link GiocatoreDAO} (gestita nel metodo readGiocatoreByList che prende come parametro la List<String>)
	 * 
	 * 2015-11-06 
	 * 	- Crea il Layout dei giocatori
	 * 	- Tutta la gestione della verifica, inserimento, e recupero dati dal database è a carica do {@link GiocatoreDAO} 
	 * 	- Modificato il parametro da (String nome) a (List<String> elencoNomi)
	 * 
	 * @param elencoNomi e' la List<String> che contiene  nomi e da cui recupero i {@link Giocatore} 
	 */
	public void inizializzaPartita(List<String> elencoNomi) {

		partita.removeAll(partita) ;

		GiocatoreDAO dao = new GiocatoreDAO();

		this.partita.addAll(dao.readGiocatoreByList(elencoNomi));
		// DEBUG Syso di controllo per verificare, grazie all'ID, se il giocatore inserito nella lista della partita viene letto dal database
//		partita.forEach(g -> System.out.println("ID: " + g.getId() + " nome: " + g.getNome()));

		creaLayout(partita);
	}

	/**
	 * Genera il layout dei giocatori
	 */
	public void creaLayout(List<Giocatore> partita) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewPartita.fxml"));
			GridPane loaderPartita = loader.load();

			RowConstraints row = new RowConstraints();
			row.setVgrow(Priority.ALWAYS);
			loaderPartita.getRowConstraints().add(row);
			
			for (int i = 0; i < partita.size(); i++) {
				ColumnConstraints col = new ColumnConstraints();
				col.setHgrow(Priority.ALWAYS);
				loaderPartita.getColumnConstraints().add(col);
				loaderPartita.add(partita.get(i).getViewGiocatore(), i, 0);
			}

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(loaderPartita,800,640));
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
