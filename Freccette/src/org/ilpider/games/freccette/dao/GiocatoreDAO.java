package org.ilpider.games.freccette.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	 * Restituisce come stringa il valore del campo NOME della TBLGIOCATORE che ha l'id specificato come parametro
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
// System.out.println("non connetto");
			return null;
		}
	}

	/**
	 * Restituisce TRUE se nel database esiste gia' un giocatore che ha il nome di quello che sta verificando se inserire o no.
	 * 
	 * @param nome
	 *            E' il nome del giocatore da verificare se e' gia' presente nel database
	 * @return TRUE se esiste un record che ha lo stesso nome, FALSE se invece non lo trova
	 */
	public boolean esisteGiocatoreByNome(String nome) {

		Connection conn = DBConnect.getConnection();
		String query = "SELECT * from `tblgiocatori` where `nome` = ?";

		try {
			PreparedStatement st1 = conn.prepareStatement(query);
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
	 * Restituisce la List<{@link Giocatore}> creata partendo dalla List<nomi> che viene passata come parametro
	 * Se un nome non e' presente nel database, lo aggiunge lanciando il metodo addGiocatore(nome) 
	 * 
	 * @param elencoNomi e' la List<String> che viene passata e che contiene i nomi dei {@link Giocatore} da cui verra' creata la List<{@link Giocatore}>
	 *  
	 * @return la List<{@link Giocatore}>
	 */
	public List<Giocatore> readGiocatoreByList(List<String> elencoNomi) {
		List<Giocatore> listGiocatori = new ArrayList<>();

		elencoNomi.forEach(n -> {
			if (!esisteGiocatoreByNome(n))
				addGiocatore(n);
		});
		elencoNomi.forEach(n -> listGiocatori.add(readGiocatoreByNome(n)));

		return listGiocatori;
	}

	/**
	 * Restituisce il {@link Giocatore} che corrisponde a quello trovato nel database con il nome specificato
	 * 
	 * @param nome
	 *            E' il nome del giocatore da trovare
	 * @return il {@link Giocatore}
	 */
	public Giocatore readGiocatoreByNome(String nome) {

		Connection conn = DBConnect.getConnection();
		String query = "SELECT * from `tblgiocatori` where `nome` = ?";

		try {
			PreparedStatement st1 = conn.prepareStatement(query);
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
	 * @param nome
	 *            nome del {@link Giocatore} da inserire nel database
	 * @return l'ID del record appena inserito. Se si verifica un errore nell'esecuzione della INSERT -> return 99; Se va male la connessione al DB -> return -1;
	 * 
	 */
	public int addGiocatore(String nome) {

		Connection conn = DBConnect.getConnection();
		String query = "INSERT INTO `tblgiocatori`(`ID`, `Nome`) VALUES (null,UPPER(?))";

		try {
			PreparedStatement st1 = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st1.setString(1, nome);

			// int rowInserted = st1.executeUpdate();
			int IDGiocatore = st1.executeUpdate();
			ResultSet rs1 = st1.getGeneratedKeys();
			if (rs1.next()) {
				IDGiocatore = rs1.getInt(1);
				/* ritorna IDGiocatore che ha valore uguale al campo ID del record del Giocatore appena inserito */
				return IDGiocatore;
			}
			/* ritorna 99 se NON e' riuscito ad inserire il Giocatore nella tabella */
			return 99;
		} catch (SQLException e) {
			e.printStackTrace();
			/* ritorna -1 se è andata male la connessione con il database */
			return -1;
		}
	}
}
