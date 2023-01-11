package ar.com.shobukai.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import ar.com.shobukai.domain.Alumno;
import ar.com.shobukai.domain.HistorialExamen;
import ar.com.shobukai.domain.dao.AlumnoDAO;
import ar.com.shobukai.domain.dao.exception.DuplicatedException;
import ar.com.shobukai.domain.dao.exception.GenericException;
import ar.com.shobukai.domain.dao.impl.AlumnoDAOJDBCMySqlImpl;

public class CreateAlumnoController {

	public static void main(String[] args) {

		Collection<HistorialExamen> historial = new ArrayList<HistorialExamen>();

		Alumno alumno = new Alumno("Lucas", "Montenegro", 39147104, LocalDate.of(1996, 1, 7), LocalDate.of(2001, 4, 1),
				"Carlos Merlassino 1025", "+5492478419611", "Arrecifes", "2° Dan", historial);
		
		AlumnoDAO aDao = new AlumnoDAOJDBCMySqlImpl();
		
		try {
			aDao.create(alumno);
		} catch (DuplicatedException | GenericException e) {
			e.printStackTrace();
		}
		
		
		
		try {
			Collection<Alumno> alumnos = aDao.findAll();
			for (Alumno a : alumnos) {
				System.out.println(a);
			}
		} catch (GenericException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
