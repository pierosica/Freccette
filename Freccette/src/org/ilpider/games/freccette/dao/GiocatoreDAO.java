package org.ilpider.games.freccette.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.ilpider.games.freccette.model.Giocatore;

/**
 * Data Access Object per manipolare la tabella TBLGIOCATORI del database DARTS
 */
public class GiocatoreDAO {

	/**
	 * Conta il numero di record nella tabella TBLGIOCATORI
	 * 
	 * @return il numero di giocatori nella tabella
	 */
	public int contaTutti() {

		Connection conn = DBConnect.getConnection();

		String query = "SELECT count(id) as tutti FROM `tblgiocatori`";
		try {
			Statement st1 = conn.createStatement();
			ResultSet rs1 = st1.executeQuery(query);
			rs1.next();
			int numeroRecord = rs1.getInt("tutti");
			rs1.close();
			conn.close();
			return numeroRecord;

		} catch (SQLException e) {
			System.out.println("non connetto");
			return -1;
		}
	}

	/**
	 * Restituisce come stringa il valore del campo NOME della TBLGIOCATORE che
	 * ha l'id specificato come parametro
	 * 
	 * @param id
	 *            è l'id del giocatore che voglio leggere dal db
	 * @return (inizialmente) la stringa letta dal campo NOME
	 */
	public String giocatoreNome(int id) {

		Connection conn = DBConnect.getConnection();

		String query = "SELECT nome FROM `tblgiocatori` where id = " + id;

		try {
			Statement st1 = conn.createStatement();
			ResultSet rs1 = st1.executeQuery(query);
			rs1.next();
			String nome = rs1.getString("nome");
			rs1.close();
			conn.close();
			return nome;
		} catch (SQLException e) {
			System.out.println("non connetto");
			return null;
		}
	}

	/**
	 * Restituisce TRUE se nel database esiste gia' un giocatore che ha il nome
	 * di quello che sta verificando se inserire o no.
	 * 
	 * @param g
	 *            E' il giocatore da verificare se e' gia' presente nel database
	 * @return TRUE se esiste un record che ha lo stesso nome, FALSE se invece
	 *         non lo trova
	 */
	public boolean esisteGiocatoreByNome(String nome) {

		Connection conn = DBConnect.getConnection();
		String query = "SELECT * from `tblgiocatori` where `nome` = ?";

		try {
			PreparedStatement st1 = conn.prepareStatement(query);
			System.out.println(query);
			st1.setString(1, nome);
			st1.execute();
			ResultSet rs1 = st1.executeQuery();

			if (rs1.next()) {
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Restituisce il {@link Giocatore} che corrisponde a quello trovato nel
	 * database con il nome specificato
	 * 
	 * @param nome
	 *            E' il nome del giocatore da trovare
	 * @return il {@link Giocatore}
	 */
	public Giocatore giocatoreByNome(String nome) {

		Connection conn = DBConnect.getConnection();
		String query = "SELECT * from `tblgiocatori` where `nome` = ?";

		try {
			PreparedStatement st1 = conn.prepareStatement(query);
			System.out.println(query);
			st1.setString(1, nome);
			st1.execute();
			ResultSet rs1 = st1.executeQuery();
			rs1.next();

			Giocatore g = new Giocatore(rs1.getInt("id"), rs1.getString("nome"));
			return g;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Aggiunge il {@link Giocatore} g nel database e restituisce il valore del campo ID del record appena inserito
	 * 
	 * @param g
	 *            {@link Giocatore} da inserire nel database
	 * @return l'ID del record appena inserito
	 */
	public int addGiocatore(String nome) {

		Connection conn = DBConnect.getConnection();
		String query = "INSERT INTO `tblgiocatori`(`ID`, `Nome`) VALUES (null,?)";

		try {
			PreparedStatement st1 = conn.prepareStatement(query);
			System.out.println(query);
			st1.setString(1, nome);
			int rowInserted = st1.executeUpdate();
			return rowInserted;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
