package co.com.mercadolibre.health.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	/**
	 * Estas variables deberian estar en variables de entorno
	 */
	private static final String POSTGRES="jdbc:postgresql://postgres.c9vc2lcfn2qx.us-east-2.rds.amazonaws.com:5432/postgres";
	private static final String USER="postgres";
	private static final String PASS="postgres";
	
	public static Connection conectar() throws SQLException {
		Connection con = null; 
		try { 
		
		    Class.forName("org.postgresql.Driver");
		    con = DriverManager.getConnection(
		    		POSTGRES,
		    		USER, PASS);
		} catch (ClassNotFoundException ex) {
		    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
		return con;
	}
}
