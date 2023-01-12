package ar.com.shobukai.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseadorFecha {

	// Dada una fecha en formato string, devuelve la fecha en formato LocalDate
	public static LocalDate ALocalDate(String fecha) {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate fechaFormateada = LocalDate.parse(fecha, formato);

		return fechaFormateada;

	}

	// Dada una fecha en formato LocalDate, devuelve la fecha en formato String
	public static String AString(LocalDate fecha) {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String fechaFormateada = fecha.format(formato);

		return fechaFormateada;

	}

}
