package ar.com.shobukai.service;

import java.util.Collection;

import ar.com.shobukai.domain.Alumno;
import ar.com.shobukai.exceptions.ServiceException;

public interface AlumnoService {
	
	public Alumno crearAlumno(Alumno alumno) throws ServiceException;

	public Alumno obtenerAlumno(int doc) throws ServiceException;
	
	public Alumno obtenerAlumno(Long id) throws ServiceException;

	public Collection<Alumno> obtenerTodos() throws ServiceException;
	
	public Alumno actualizarAlumno(Alumno alumno) throws ServiceException;

	public Alumno eliminarAlumno(int doc) throws ServiceException;

}
