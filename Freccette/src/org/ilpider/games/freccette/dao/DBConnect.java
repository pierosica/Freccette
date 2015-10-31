package org.ilpider.games.freccette.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce la connessione al db
 * 
 * @author Piero Sica
 *
 */
public class DBConnect {

	private static String url = "jdbc:mysql://localhost/darts?user=mysqluser&password=qwerty12";

	/**
	 * Restituisce una nuova connessione con i parametri che conosce
	 * 
	 * @return la nuova java.sql.Connection, oppure null se ci sono errori
	 */
	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}