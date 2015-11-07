package org.ilpider.games.freccette.model;

import java.util.ArrayList;
import java.util.List;

import org.ilpider.games.freccette.view.ViewGiocatoreController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

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
	private GridPane viewGiocatore;
	private RigaNumero rigaNumeroFix;
	private List<RigaNumero> listRigaNumero;

	public Giocatore(int id, String nome) {
		super();
		this.setId(id);
		this.nome = nome;
		this.punti = 0;
		setViewGiocatore();
		this.viewGiocatore = getViewGiocatore();
		creaListRigaNumero();
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

	public GridPane getViewGiocatore() {
		return viewGiocatore;
	}

	private void setViewGiocatore() {
		try {
			FXMLLoader loaderViewGiocatore = new FXMLLoader();
			loaderViewGiocatore.setLocation(getClass().getResource("../view/ViewGiocatore.fxml"));
			this.viewGiocatore = loaderViewGiocatore.load();

			ViewGiocatoreController controller = loaderViewGiocatore.getController();
			controller = loaderViewGiocatore.getController();
			controller.setGiocatoreModel(this);
// controller.setLblNome(this.nome);
			controller.setLbl1Nome();
			controller.setLblPunti(this.punti);

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void creaListRigaNumero() {

		listRigaNumero = new ArrayList<RigaNumero>();

		int numeri = 21;
		for (int i = 0; i < numeri; i++) {
			rigaNumeroFix = new RigaNumero(i);
			rigaNumeroFix.setGiocatoreModel(this);
			rigaNumeroFix.getLayoutRigaNumero().autosize();
			listRigaNumero.add(rigaNumeroFix);

			RowConstraints row = new RowConstraints();
			row.setVgrow(Priority.ALWAYS);
			viewGiocatore.getRowConstraints().add(row);

			viewGiocatore.add(rigaNumeroFix.getLayoutRigaNumero(), 0, 1 + i);
		}
	}
}
