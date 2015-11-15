package org.ilpider.games.freccette.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

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
 * Gestisce la logica del gioco. Prende e memorizza i dati nel database chiamando i metodi della classe {@link GiocatoreDAO}
 * 
 * @author Piero Sica
 *
 */
public class PartitaModel {

	private List<Giocatore> listaGiocatori;

	/**
	 * Il costruttore crea tutte le volte una nuova lista di {@link Giocatore}
	 */
	public PartitaModel() {
		this.listaGiocatori = new LinkedList<Giocatore>();
	}

	/**
	 * Svuota la propria List<{@link Giocatore}> e la popola con quella nuova creata da {@link GiocatoreDAO}. Crea il layout della partita
	 * 
	 * 2015-11-06 - Crea il Layout dei giocatori - Tutta la gestione della verifica, inserimento, e recupero {@link Giocatore} dal database è a carico di {@link GiocatoreDAO} - Modificato il parametro da (String nome) a (List<String> elencoNomi)
	 * 
	 * @param elencoNomi
	 *            e' la List<String> che contiene i nomi di quelli che saranno i {@link Giocatore}.
	 */
	public void inizializzaPartita(List<String> elencoNomi) {

		listaGiocatori.removeAll(listaGiocatori);
		GiocatoreDAO dao = new GiocatoreDAO();
		this.listaGiocatori.addAll(dao.readGiocatoreByList(elencoNomi));
		listaGiocatori.forEach(g -> g.setPartitaModel(this));
		creaLayoutGiocatori(listaGiocatori);
	}

	/**
	 * Genera il layout dei giocatori
	 */
	private void creaLayoutGiocatori(List<Giocatore> elencoGiocatori) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewPartita.fxml"));
			GridPane loaderPartita = loader.load();

			// Senza questa RowConstraints il LayoutGiocaotre non riempie l'altezza del LayoutPartita
			RowConstraints row = new RowConstraints();
			row.setVgrow(Priority.ALWAYS);
			loaderPartita.getRowConstraints().add(row);

			for (int i = 0; i < elencoGiocatori.size(); i++) {
				ColumnConstraints col = new ColumnConstraints();
				col.setHgrow(Priority.ALWAYS);
				loaderPartita.getColumnConstraints().add(col);
				loaderPartita.add(elencoGiocatori.get(i).getViewGiocatore(), i, 0);
			}

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			// stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("ABC");
			stage.setScene(new Scene(loaderPartita, 800, 640));
			stage.show();
		} catch (Exception e) {
			System.out.println("qualcosa non va");
			e.printStackTrace();
		}
	}

	/**
	 * Controlla se tutti i {@link Giocatore} hanno idRiga isChiuso=true
	 * 
	 * @param idRiga
	 *            e' l'ID della riga da verificare se isMorto
	 * 
	 * @return TRUE se tutti hanno chiuso idRiga; FALSE se NON tutti hanno chiuso idRiga
	 */
	public boolean isNumeroMorto(int idRiga) {

		if (listaGiocatori.stream().allMatch(g -> g.getListRigaNumero().get(idRiga).isChiuso())) {
			Stream<Giocatore> giocatoriNumeroChiuso = listaGiocatori.stream().filter(c -> c.getListRigaNumero().get(idRiga).isChiuso());
			giocatoriNumeroChiuso.forEach(n -> n.getListRigaNumero().get(idRiga).setMorto(true));
			return true;
		} else {
			listaGiocatori.forEach(n -> n.getListRigaNumero().get(idRiga).setMorto(false));
			return false;
		}
	}

	public void sommaPunti(int idRiga) {
			Stream<Giocatore> giocatoriDaCaricare = listaGiocatori.stream().filter(c -> !c.getListRigaNumero().get(idRiga).isChiuso());
			giocatoriDaCaricare.forEach(c -> c.setPunti(c.getPunti().get() + c.getListRigaNumero().get(idRiga).getNumero()));
	}
}
