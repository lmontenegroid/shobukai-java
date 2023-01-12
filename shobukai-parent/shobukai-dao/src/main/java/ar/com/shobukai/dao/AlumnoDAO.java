package ar.com.shobukai.dao;

import java.util.Collection;

import ar.com.shobukai.dao.exception.DuplicatedException;
import ar.com.shobukai.dao.exception.GenericException;
import ar.com.shobukai.dao.exception.NonExistsException;
import ar.com.shobukai.domain.Alumno;

public interface AlumnoDAO {

	//CRUD
	
	//Create
	public Alumno create(Alumno alumno) throws DuplicatedException, GenericException;

	//Read
	public Alumno getById(Long id) throws GenericException;
	//Read
	public Alumno getByDocumento(int doc) throws GenericException;
	//Read
	public Collection<Alumno> findAll() throws GenericException;
	//Read
	public Collection<Alumno> findAllByApellido(String apellido) throws GenericException;

	//Update
	public Alumno update(Alumno alumno) throws NonExistsException, DuplicatedException, GenericException;

	//Delete
	public Alumno deleteByDocumento(int doc) throws GenericException, NonExistsException;

}
