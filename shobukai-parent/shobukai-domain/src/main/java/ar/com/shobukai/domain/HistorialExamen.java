package ar.com.shobukai.domain;

import java.time.LocalDate;

//Representa el historial de exámenes de un alumno en la base de datos
public class HistorialExamen {

	// Atributos
	private LocalDate fechaExamen;
	private String graduacion;

	// Constructor con todos los parámetros
	public HistorialExamen(LocalDate fechaExamen, String graduacion) {
		super();
		this.fechaExamen = fechaExamen;
		this.graduacion = graduacion;
	}

	// Getters y Setters
	public LocalDate getFechaExamen() {
		return fechaExamen;
	}

	public void setFechaExamen(LocalDate fechaExamen) {
		this.fechaExamen = fechaExamen;
	}

	public String getGraduacion() {
		return graduacion;
	}

	public void setGraduacion(String graduacion) {
		this.graduacion = graduacion;
	}

}
