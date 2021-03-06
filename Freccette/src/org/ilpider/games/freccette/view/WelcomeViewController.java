package org.ilpider.games.freccette.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.ilpider.games.freccette.model.PartitaModel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class WelcomeViewController {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private Button btnNuovaPartita;
	@FXML
	private HBox hbox;
	@FXML
	private TextField txtNome1;
	@FXML
	private TextField txtNome2;
	@FXML
	private TextField txtNome3;
	@FXML
	private TextField txtNome4;

	private List<TextField> listNomi;
	private PartitaModel partitaModel;

	@FXML
	void initialize() {
		assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert txtNome2 != null : "fx:id=\"txtNome2\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert txtNome1 != null : "fx:id=\"txtNome1\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert txtNome4 != null : "fx:id=\"txtNome4\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert txtNome3 != null : "fx:id=\"txtNome3\" was not injected: check your FXML file 'Welcome.fxml'.";

		/*
		 * con i bind controllo la proprieta' isDisabled delle txtNome3 e txtNome4 e del btnNuovaéartita
		 */
		txtNome3.disableProperty().bind(txtNome2.textProperty().isEmpty());
		txtNome4.disableProperty().bind(txtNome3.textProperty().isEqualTo(""));
		btnNuovaPartita.disableProperty()
				.bind(Bindings.or(txtNome1.textProperty().isEmpty(), txtNome2.textProperty().isEmpty()));

		/*
		 * Creo un ArrayList in cui metto le txtNome* in modo da poter ciclare sulla lista per vedere quali giocatori gestire
		 */
		listNomi = new ArrayList<>();
		listNomi.add(txtNome1);
		listNomi.add(txtNome2);
		listNomi.add(txtNome3);
		listNomi.add(txtNome4);

		/*
		 * Listener per evitare che il focus vada su txtNome1 all'avvio del programma
		 */
		final BooleanProperty firstTime = new SimpleBooleanProperty(true);
		txtNome1.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && firstTime.get()) {
				hbox.requestFocus(); // Delegate the focus to container
				firstTime.setValue(false); // Variable value changed for future references
			}
		});
	}

	/**
	 * @FXML - * Crea un ArraiList<String> e lo avvalora con TextField.getText() delle TextField che hanno isEmpty=false e lo passa al metodo inizializzaPartita del partitaModel.
	 * 
	 * @param event
	 */
	@FXML
	void doNuovaPartita(ActionEvent event) {
		List<String> elencoNomi = new ArrayList<>();
		listNomi.forEach(tf -> {
			if (!tf.getText().isEmpty()) {
				elencoNomi.add(tf.getText());
			}
		});
		partitaModel.inizializzaPartita(elencoNomi);
	}

	public void setPartitaModel(PartitaModel partitaModel) {
		this.partitaModel = partitaModel;
	}
}
