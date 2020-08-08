package co.com.mercadolibre.health.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	
	public static Connection conectar() throws SQLException {
		Connection con = null; 
		try { 
			String prueba = ParametersUtils.getVariable("URL_POSTGRES");
		    Class.forName("org.postgresql.Driver");
		    con = DriverManager.getConnection(
		            "jdbc:postgresql://localhost:5432/postgres",
		            "postgres", "postgres");
		} catch (ClassNotFoundException ex) {
		    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
		return con;
	}
}
