package org.ilpider.games.freccette.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.ilpider.games.freccette.model.RigaNumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerLayoutRigaNumero {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
    @FXML
    private Label lblA;
    @FXML
    private Label lblB;
    @FXML
    private Label lblC;
	@FXML
	private Button btnPreso;
	@FXML
	private Button btnTogli;

//	private int IDRiga;
//	private int numero;
	private RigaNumero rigaNumeroModel;

	@FXML
	void doBtnPreso(ActionEvent event) {
		if (rigaNumeroModel.isMorto()) {
			rigaNumeroModel.getGiocatoreModel().setPunti(rigaNumeroModel.getGiocatoreModel().getPunti().get() + getNumero());
		} else if (rigaNumeroModel.isChiuso()) {
			rigaNumeroModel.getGiocatoreModel().getPartitaModel().sommaPunti(getIDRiga());
		} else 	if (!lblA.getUserData().equals("preso")) {
			lblA.setUserData("preso");
			lblA.getStyleClass().add("lbl-presa");
		} else if (!lblB.getUserData().equals("preso")) {
			lblB.setUserData("preso");
			lblB.getStyleClass().add("lbl-presa");
		} else if (!lblC.getUserData().equals("preso")) {
			lblC.setUserData("preso");
			lblC.getStyleClass().add("lbl-presa");
			rigaNumeroModel.setChiuso(true);
			rigaNumeroModel.getGiocatoreModel().getPartitaModel().isNumeroMorto(getIDRiga());
		}
	}

	@FXML
	void doBtnTogli(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert btnTogli != null : "fx:id=\"btnTogli\" was not injected: check your FXML file 'LayoutRigaNumero.fxml'.";
		assert btnPreso != null : "fx:id=\"btnPreso\" was not injected: check your FXML file 'LayoutRigaNumero.fxml'.";

//		btnPreso.setText("" + rigaNumeroModel.getNumero());
//		btnPreso.setText("" + getNumero());
//		if (getNumero() == 25) {
//			btnPreso.setText("Bull");
//		}

		lblA.setUserData("");
		lblB.setUserData("");
		lblC.setUserData("");

		
	}

	/*
	 * metodi
	 */
	public void setMorto() {
		
		lblA.getStyleClass().add("lbl-morta");
		lblB.getStyleClass().add("lbl-morta");
		lblC.getStyleClass().add("lbl-morta");
//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println(rigaNumeroModel.isMorto() + "setChkMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}

//		lblA.setIndeterminate(true);
//		lblB.setIndeterminate(true);
//		lblC.setIndeterminate(true);

//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println("setChkMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}
	}

	public void setNonMorto() {

		lblA.getStyleClass().add("lbl-chiusa");
		lblB.getStyleClass().add("lbl-chiusa");
		lblC.getStyleClass().add("lbl-chiusa");
//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println("setChkNonMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}

//		chkA.setIndeterminate(false);
//		chkB.setIndeterminate(false);
//		chkC.setIndeterminate(false);

//		for (Node n : rigaNumeroModel.getLayoutRigaNumero().lookupAll(".check-box")) {
//			System.out.println("setChkNonMorto  trovato in controller " + n.getId() + " " + n.getPseudoClassStates());
//		}
	}

	public int chkAperte() {
		int n = 0;
		if (lblA.getUserData().equals("")) {
			n = n + 1;
		}
		if (lblB.getUserData().equals("")) {
			n = n + 1;
		}
		if (lblC.getUserData().equals("")) {
			n = n + 1;
		}
		return n;
	}

	public int getIDRiga() {
		return getRigaNumeroModel().getIdRiga();
	}
	
	public int getNumero() {
		return this.getRigaNumeroModel().getNumero();
	}
	
	/*
	 * Getters e Setters
	 */
	public RigaNumero getRigaNumeroModel() {
		return rigaNumeroModel;
	}

	public void setRigaNumeroModel(RigaNumero rigaNumeroModel) {
		this.rigaNumeroModel = rigaNumeroModel;
	}
}
