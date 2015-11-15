package org.ilpider.games.freccette.model;

import java.util.ArrayList;
import java.util.List;

import org.ilpider.games.freccette.view.ViewGiocatoreController;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
//	private int punti;
	private IntegerProperty punti;
	private GridPane viewGiocatore;
	private RigaNumero rigaNumero;
	private List<RigaNumero> listRigaNumero;
	private PartitaModel partitaModel;

	public Giocatore(int id, String nome) {
		super();
		this.setId(id);
		this.nome = nome;
//		this.punti = 0;
		this.punti = new SimpleIntegerProperty(0);
		setViewGiocatore();
//		this.viewGiocatore = getViewGiocatore();
		creaListRigaNumero();
	}

	/* getters and setters, hash code and equals */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public int getPunti() {
//		return punti;
//	}
//
//	public void setPunti(int punti) {
//		this.punti = punti;
//	}

	public IntegerProperty getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti.set(punti);
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
			controller.setLblNome();
			controller.setLblPunti(this.punti.get());

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creaListRigaNumero() {

		listRigaNumero = new ArrayList<RigaNumero>();

		int numeri = 21;
		for (int i = 0; i < numeri; i++) {
			rigaNumero = new RigaNumero(i);
			rigaNumero.setGiocatoreModel(this);
			rigaNumero.getLayoutRigaNumero().autosize();
//			rigaNumero.setId(i + 1);
			listRigaNumero.add(rigaNumero);

			RowConstraints row = new RowConstraints();
			row.setVgrow(Priority.ALWAYS);
			viewGiocatore.getRowConstraints().add(row);

			viewGiocatore.add(rigaNumero.getLayoutRigaNumero(), 0, 1 + i);
		}
	}

	public List<RigaNumero> getListRigaNumero() {
		return this.listRigaNumero;
	}

	public PartitaModel getPartitaModel() {
		return this.partitaModel;
	}

	public void setPartitaModel(PartitaModel partitaModel) {
		this.partitaModel = partitaModel;
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
}
