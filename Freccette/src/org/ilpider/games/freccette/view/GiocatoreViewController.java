package org.ilpider.games.freccette.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.ilpider.games.freccette.model.Giocatore;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GiocatoreViewController {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private Label lblPunti;
	@FXML
	private Label lblNome;

	private Giocatore giocatoreModel;

	public void setGiocatoreModel(Giocatore giocatoreModel) {

		this.giocatoreModel = giocatoreModel;
		
		lblPunti.textProperty().bind(Bindings.convert(giocatoreModel.getPunti()));
	}

	@FXML
	void initialize() {
		assert lblPunti != null : "fx:id=\"lblPunti\" was not injected: check your FXML file 'ViewGiocatore.fxml'.";
		assert lblNome != null : "fx:id=\"lblNome\" was not injected: check your FXML file 'ViewGiocatore.fxml'.";
	}

	public void setLblNome() {
		lblNome.setText(giocatoreModel.getNome());
	}

//	public void setLblPunti(int punti) {
//		lblPunti.textProperty().bind(giocatoreModel.getPunti().asString());
//	}
}