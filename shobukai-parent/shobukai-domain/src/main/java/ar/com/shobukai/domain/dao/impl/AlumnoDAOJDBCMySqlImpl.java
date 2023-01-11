package ar.com.shobukai.domain.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import ar.com.shobukai.domain.Alumno;
import ar.com.shobukai.domain.dao.AdministradorDeConexiones;
import ar.com.shobukai.domain.dao.AlumnoDAO;
import ar.com.shobukai.domain.dao.exception.DuplicatedException;
import ar.com.shobukai.domain.dao.exception.GenericException;

public class AlumnoDAOJDBCMySqlImpl implements AlumnoDAO {

	// Atributo donde se guarda la conexión con la db
	private Connection connection;

	// Constructor que hace la conexión con la DB, no sigue ejecutando en caso
	// de fallar la conexión
	// Si la conexión es válida, se utilizan las implementaciones
	public AlumnoDAOJDBCMySqlImpl() {
		try {
			this.connection = AdministradorDeConexiones.obtenerConexion();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Crea un alumno dentro de la base de datos
	@Override
	public Alumno create(Alumno alumno) throws DuplicatedException, GenericException {
		String sql = "INSERT INTO alumnos (nombre, apellido, documento, fechaNac, "
				+ "fechaIng, domicilio, telefono, dojo, graduacion) VALUES ('" + alumno.getNombre() + "', '"
				+ alumno.getApellido() + "', '" + alumno.getDocumento() + "', '" + alumno.getFechaNac() + "', '"
				+ alumno.getFechaIng() + "', '" + alumno.getDomicilio() + "', '" + alumno.getTelefono() + "', '"
				+ alumno.getDojo() + "', '" + alumno.getGraduacion() + "')";

		try {
			PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			st.execute();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				Long pk = rs.getLong(1);
				alumno.setId(pk);
			}

			return alumno;
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new DuplicatedException("Clave duplicada, no se ha registrado el alumno", e);
			}
			throw new GenericException("No se ha podido crear el alumno", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
			}
		}
	}

	// Devuelve todos los alumnos dentro de la base de datos
	@Override
	public Collection<Alumno> findAll() throws GenericException {

		Collection<Alumno> alumnos = new ArrayList<Alumno>();

		String sql = "SELECT * FROM alumnos";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong(1); // PK
				String nombre = rs.getString(2); // nombre
				String apellido = rs.getString(3); // apellido
				int documento = rs.getInt(4); // documento
				LocalDate fechaNac = rs.getDate(5).toLocalDate(); // Fecha de nacimiento
				LocalDate fechaIng = rs.getDate(6).toLocalDate(); // Fecha de ingreso
				String domicilio = rs.getString(7); // domicilio
				String telefono = rs.getString(8); // telefono
				String dojo = rs.getString(9); // dojo
				String graduacion = rs.getString(10); // graduacion

				Alumno alumno = new Alumno(id, nombre, apellido, documento, fechaNac, fechaIng, domicilio, telefono,
						dojo, graduacion);

				alumnos.add(alumno);
			}
			return alumnos;

		} catch (SQLException e) {
			throw new GenericException("No se ha crear la lista de alumnos", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
			}

		}

	}
}
