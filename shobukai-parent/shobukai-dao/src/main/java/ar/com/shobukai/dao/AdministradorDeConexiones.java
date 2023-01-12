package ar.com.shobukai.dao;

import java.sql.Connection;
import java.sql.DriverManager;

//Administra la conexión contra la base de datos
public class AdministradorDeConexiones {

	public static Connection obtenerConexion() throws Exception {
		// Dirección de la clase del driver a conectar
		String driver = "com.mysql.cj.jdbc.Driver";

		// String de conexión a la DB
		String dbConnectionUrl = "jdbc:mysql://127.0.0.1/shobukai?serverTimeZone=UTC&useSSL=false";

		// Usuario
		String username = "root";

		// Contraseña
		String password = "root";

		try {
			Class.forName(driver);

			Connection connection = DriverManager.getConnection(dbConnectionUrl, username, password);
			return connection;
		} catch (Exception e) {
			throw e;
		}

	}
}
