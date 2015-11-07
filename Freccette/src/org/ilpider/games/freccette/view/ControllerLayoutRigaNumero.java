package org.ilpider.games.freccette.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.ilpider.games.freccette.model.RigaNumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class ControllerLayoutRigaNumero {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private CheckBox chkA;
	@FXML
	private CheckBox chkB;
	@FXML
	private CheckBox chkC;
	@FXML
	private Button btnPreso;
	@FXML
	private Button btnTogli;

	private int IDNumero;
	private int numero;
	private RigaNumero rigaNumeroModel;

	@FXML
	void doBtnPreso(ActionEvent event) {

	}

	@FXML
	void doBtnTogli(ActionEvent event) {

	}

	@FXML
	void initialize() {

		assert chkB != null : "fx:id=\"chkB\" was not injected: check your FXML file 'LayoutRigaNumero.fxml'.";
		assert chkA != null : "fx:id=\"chkA\" was not injected: check your FXML file 'LayoutRigaNumero.fxml'.";
		assert chkC != null : "fx:id=\"chkC\" was not injected: check your FXML file 'LayoutRigaNumero.fxml'.";
		assert btnTogli != null : "fx:id=\"btnTogli\" was not injected: check your FXML file 'LayoutRigaNumero.fxml'.";
		assert btnPreso != null : "fx:id=\"btnPreso\" was not injected: check your FXML file 'LayoutRigaNumero.fxml'.";
		chkA.setUserData("");
		chkB.setUserData("");
		chkC.setUserData("");

	}

	/*
	 * metodi
	 */
	public void setChkMorto() {

//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println(rigaNumeroModel.isMorto() + "setChkMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}

		chkA.setIndeterminate(true);
		chkB.setIndeterminate(true);
		chkC.setIndeterminate(true);

//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println("setChkMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}
	}

	public void setChkNonMorto() {

//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println("setChkNonMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}

		chkA.setIndeterminate(false);
		chkB.setIndeterminate(false);
		chkC.setIndeterminate(false);

//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println("setChkNonMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}
	}

	public int chkAperte() {
		int n = 0;
		if (chkA.getUserData().equals("")) {
			n = n + 1;
		}
		if (chkB.getUserData().equals("")) {
			n = n + 1;
		}
		if (chkC.getUserData().equals("")) {
			n = n + 1;
		}
		return n;
	}

	/*
	 * Getters e Setters
	 */
	public int getIDNumero() {
		return IDNumero;
	}

	public void setIDNumero(int numero) {
		this.IDNumero = numero;
	}

	public RigaNumero getRigaNumeroModel() {
		return rigaNumeroModel;
	}

	public void setRigaNumeroModel(RigaNumero rigaNumeroModel) {
		this.rigaNumeroModel = rigaNumeroModel;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int valore) {
		btnPreso.setText("" + valore);
		if (valore == 25) {
			btnPreso.setText("Bull");
		}
		this.numero = valore;
	}
}
