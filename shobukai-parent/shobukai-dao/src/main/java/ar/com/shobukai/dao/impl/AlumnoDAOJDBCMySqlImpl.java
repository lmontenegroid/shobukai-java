package ar.com.shobukai.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import ar.com.shobukai.dao.AdministradorDeConexiones;
import ar.com.shobukai.dao.AlumnoDAO;
import ar.com.shobukai.dao.exception.DuplicatedException;
import ar.com.shobukai.dao.exception.GenericException;
import ar.com.shobukai.dao.exception.NonExistsException;
import ar.com.shobukai.domain.Alumno;

public class AlumnoDAOJDBCMySqlImpl implements AlumnoDAO {

	// Constructor
	public AlumnoDAOJDBCMySqlImpl() {

	}

	// Genera una conexión con la DB
	private Connection getCurrentConnection() throws GenericException {
		try {
			return AdministradorDeConexiones.obtenerConexion();
		} catch (Exception e) {
			throw new GenericException("No se ha podido obtener una conexión", e);
		}
	}

	// Recibe un alumno, lo inserta en la DB y devuelve el alumno
	@Override
	public Alumno create(Alumno alumno) throws DuplicatedException, GenericException {
		String sql = "INSERT INTO alumnos (nombre, apellido, documento, fechaNac, "
				+ "fechaIng, domicilio, telefono, dojo, graduacion) VALUES ('" + alumno.getNombre() + "', '"
				+ alumno.getApellido() + "', '" + alumno.getDocumento() + "', '" + alumno.getFechaNac() + "', '"
				+ alumno.getFechaIng() + "', '" + alumno.getDomicilio() + "', '" + alumno.getTelefono() + "', '"
				+ alumno.getDojo() + "', '" + alumno.getGraduacion() + "')";

		Connection connection = getCurrentConnection();
		try {

			connection.setAutoCommit(false);

			PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			st.execute();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				Long pk = rs.getLong(1);
				alumno.setId(pk);
			}

			connection.commit();
			return alumno;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new GenericException("No se ha podido hacer rollback", e1);
			}
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new DuplicatedException("Clave duplicada, no se ha registrado el alumno", e);
			}
			throw new GenericException("No se ha podido crear el alumno", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
			}
		}
	}

	// Recibe un id, si existe en la DB devuelve el alumno o null
	@Override
	public Alumno getById(Long id) throws GenericException {
		Alumno alumno = null;

		String sql = "SELECT * FROM alumnos WHERE id = " + id;

		Connection connection = getCurrentConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long pk = rs.getLong(1); // PK
				String nombre = rs.getString(2); // nombre
				String apellido = rs.getString(3); // apellido
				int documento = rs.getInt(4); // documento
				LocalDate fechaNac = rs.getDate(5).toLocalDate(); // Fecha de nacimiento
				LocalDate fechaIng = rs.getDate(6).toLocalDate(); // Fecha de ingreso
				String domicilio = rs.getString(7); // domicilio
				String telefono = rs.getString(8); // telefono
				String dojo = rs.getString(9); // dojo
				String graduacion = rs.getString(10); // graduacion

				alumno = new Alumno(pk, nombre, apellido, documento, fechaNac, fechaIng, domicilio, telefono, dojo,
						graduacion);
			}
			return alumno;

		} catch (SQLException e) {
			throw new GenericException("No se podido obtener el alumno", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
			}

		}
	}

	// Recibe un documento, si existe en la DB devuelve el alumno o null
	@Override
	public Alumno getByDocumento(int doc) throws GenericException {
		Alumno alumno = null;

		String sql = "SELECT * FROM alumnos WHERE documento = " + doc;

		Connection connection = getCurrentConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long pk = rs.getLong(1); // PK
				String nombre = rs.getString(2); // nombre
				String apellido = rs.getString(3); // apellido
				int documento = rs.getInt(4); // documento
				LocalDate fechaNac = rs.getDate(5).toLocalDate(); // Fecha de nacimiento
				LocalDate fechaIng = rs.getDate(6).toLocalDate(); // Fecha de ingreso
				String domicilio = rs.getString(7); // domicilio
				String telefono = rs.getString(8); // telefono
				String dojo = rs.getString(9); // dojo
				String graduacion = rs.getString(10); // graduacion

				alumno = new Alumno(pk, nombre, apellido, documento, fechaNac, fechaIng, domicilio, telefono, dojo,
						graduacion);
			}
			return alumno;

		} catch (SQLException e) {
			throw new GenericException("No se podido obtener el alumno", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
			}

		}
	}

	// Recibe un apellido, devuelve una colección de alumnos con ese apellido
	@Override
	public Collection<Alumno> findAllByApellido(String apelli) throws GenericException {
		Collection<Alumno> alumnos = new ArrayList<Alumno>();

		String sql = "SELECT * FROM alumnos WHERE UPPER(apellido) like '%" + apelli.toUpperCase() + "%'";
		Connection connection = getCurrentConnection();
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
				connection.close();
			} catch (SQLException e) {
				throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
			}

		}
	}

	// Devuelve todos los alumnos de la DB
	@Override
	public Collection<Alumno> findAll() throws GenericException {

		Collection<Alumno> alumnos = new ArrayList<Alumno>();

		String sql = "SELECT * FROM alumnos";

		Connection connection = getCurrentConnection();
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
				connection.close();
			} catch (SQLException e) {
				throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
			}

		}

	}

	// Recibe un alumno, modifica a los valores nuevos si existe el alumno en la DB y lo devuelve
	@Override
	public Alumno update(Alumno alumno) throws NonExistsException, DuplicatedException, GenericException {
		Alumno alumnoBuscado = getByDocumento(alumno.getDocumento());

		if (alumnoBuscado == null) {
			throw new NonExistsException("No existe el alumno con documento " + alumno.getDocumento());
		} else {

			alumnoBuscado.setNombre(alumno.getNombre());
			alumnoBuscado.setApellido(alumno.getApellido());
			alumnoBuscado.setDocumento(alumno.getDocumento()); // duplicated
			alumnoBuscado.setFechaNac(alumno.getFechaNac());
			alumnoBuscado.setFechaIng(alumno.getFechaIng());
			alumnoBuscado.setDomicilio(alumno.getDomicilio());
			alumnoBuscado.setTelefono(alumno.getTelefono());
			alumnoBuscado.setDojo(alumno.getDojo());
			alumnoBuscado.setGraduacion(alumno.getGraduacion());

			// sql
			String sql = "UPDATE alumnos SET nombre = ?, apellido = ?, documento = ?,"
					+ "fechaNac = ?, fechaIng = ?, domicilio = ?, telefono = ?, dojo = "
					+ "?, graduacion = ? WHERE documento = ?";

			Connection connection = getCurrentConnection();
			try {
				connection.setAutoCommit(false);
				PreparedStatement ps = connection.prepareStatement(sql);

				// setear en cada ? el valor correspondiente
				ps.setString(2, alumno.getNombre());
				ps.setString(3, alumno.getApellido());
				ps.setInt(4, alumno.getDocumento());
				ps.setDate(5, Date.valueOf(alumno.getFechaNac()));
				ps.setDate(6, Date.valueOf(alumno.getFechaIng()));
				ps.setString(7, alumno.getDomicilio());
				ps.setString(8, alumno.getTelefono());
				ps.setString(9, alumno.getDojo());
				ps.setString(10, alumno.getGraduacion());

				int resultado = ps.executeUpdate();
				if (resultado < 0) {
					throw new GenericException("No se ha actualizado el alumno", null);
				}

				connection.commit();
			} catch (Exception e) {
				throw new GenericException("No se ha actualizado el alumno", e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
				}

			}

			return alumnoBuscado;
		}
	}

	// Recibe un documento, si existe en la DB elimina el alumno y lo devuelve
	@Override
	public Alumno deleteByDocumento(int doc) throws GenericException, NonExistsException {
		Alumno alumnoBuscado = getByDocumento(doc);

		if (alumnoBuscado == null) {
			throw new NonExistsException("No existe el alumno con documento " + doc);
		} else {

			String sql = "DELETE FROM alumnos WHERE documento = ?";

			Connection connection = getCurrentConnection();
			try {
				connection.setAutoCommit(false);
				PreparedStatement ps = connection.prepareStatement(sql);

				ps.setInt(1, doc);

				connection.commit();
			} catch (Exception e) {
				throw new GenericException("No se ha podido eliminar el alumno", e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new GenericException("Problema cerrando la conexión, verifique la BD", e);
				}

			}

		}
		return alumnoBuscado;
	}
}
