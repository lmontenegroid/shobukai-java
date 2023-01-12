package ar.com.shobukai.service.impl;

import java.util.Collection;

import ar.com.shobukai.dao.AlumnoDAO;
import ar.com.shobukai.dao.exception.DuplicatedException;
import ar.com.shobukai.dao.exception.GenericException;
import ar.com.shobukai.dao.exception.NonExistsException;
import ar.com.shobukai.dao.impl.AlumnoDAOJDBCMySqlImpl;
import ar.com.shobukai.domain.Alumno;
import ar.com.shobukai.exceptions.ServiceException;
import ar.com.shobukai.service.AlumnoService;

public class AlumnoServiceImpl implements AlumnoService {

	//Capa de servicios
	
	private AlumnoDAO dao;

	public AlumnoServiceImpl() {
		dao = new AlumnoDAOJDBCMySqlImpl();
	}

	@Override
	public Alumno crearAlumno(Alumno alumno) throws ServiceException {
		try {
			return this.dao.create(alumno);
		} catch (GenericException | DuplicatedException e) {
			e.printStackTrace();
			throw new ServiceException("Error creando el alumno", e);
		}

	}

	@Override
	public Alumno obtenerAlumno(Long id) throws ServiceException {
		try {
			return this.dao.getById(id);
		} catch (GenericException e) {

			e.printStackTrace();
			throw new ServiceException("Error obteniendo el alumno", e);
		}
	}

	@Override
	public Alumno obtenerAlumno(int doc) throws ServiceException {
		try {
			return this.dao.getByDocumento(doc);
		} catch (GenericException e) {

			e.printStackTrace();
			throw new ServiceException("Error obteniendo el alumno", e);
		}
	}

	@Override
	public Collection<Alumno> obtenerTodos() throws ServiceException {

		try {
			return this.dao.findAll();
		} catch (GenericException e) {

			e.printStackTrace();
			throw new ServiceException("Error obteniendo los alumnos", e);
		}
	}
	
	@Override
	public Alumno actualizarAlumno(Alumno alumno) throws ServiceException {
		try {
			return this.dao.update(alumno);
		} catch (GenericException | NonExistsException | DuplicatedException e) {

			throw new ServiceException("Error actualizando el alumno", e);
		}
	}

	@Override
	public Alumno eliminarAlumno(int doc) throws ServiceException {
		try {
			return this.dao.deleteByDocumento(doc);
		} catch (GenericException | NonExistsException e) {

			throw new ServiceException("Error eliminando el alumno", e);
		}
	}

	

}
