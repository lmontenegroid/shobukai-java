package ar.com.shobukai.domain.dao;

import java.util.Collection;

import ar.com.shobukai.domain.Alumno;
import ar.com.shobukai.domain.dao.exception.DuplicatedException;
import ar.com.shobukai.domain.dao.exception.GenericException;

public interface AlumnoDAO {

	public Alumno create(Alumno alumno) throws DuplicatedException, GenericException; 
	
	public Collection<Alumno> findAll() throws GenericException;
}
