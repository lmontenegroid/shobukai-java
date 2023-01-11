package ar.com.shobukai.domain;

import java.time.LocalDate;
import java.util.Collection;

//Representa a los alumnos en la base de datos
public class Alumno {

	// Atributos del alumno
	private Long id;
	private String nombre;
	private String apellido;
	private int documento;
	private LocalDate fechaNac;
	private LocalDate fechaIng;
	private String domicilio;
	private String telefono;
	private String dojo;
	private String graduacion;
	private Collection<HistorialExamen> historial;

	// Constructor con todos los parámetros
	public Alumno(Long id, String nombre, String apellido, int documento, LocalDate fechaNac, LocalDate fechaIng,
			String domicilio, String telefono, String dojo, String graduacion, Collection<HistorialExamen> historial) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.fechaNac = fechaNac;
		this.fechaIng = fechaIng;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.dojo = dojo;
		this.graduacion = graduacion;
		this.historial = historial;
	}

	// Constructor sin id
	public Alumno(String nombre, String apellido, int documento, LocalDate fechaNac, LocalDate fechaIng,
			String domicilio, String telefono, String dojo, String graduacion, Collection<HistorialExamen> historial) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.fechaNac = fechaNac;
		this.fechaIng = fechaIng;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.dojo = dojo;
		this.graduacion = graduacion;
		this.historial = historial;
	}

	// Constructor sin historial
	public Alumno(Long id, String nombre, String apellido, int documento, LocalDate fechaNac, LocalDate fechaIng,
			String domicilio, String telefono, String dojo, String graduacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.fechaNac = fechaNac;
		this.fechaIng = fechaIng;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.dojo = dojo;
		this.graduacion = graduacion;
	}

	// toString
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
				+ ", fechaNac=" + fechaNac + ", fechaIng=" + fechaIng + ", domicilio=" + domicilio + ", telefono="
				+ telefono + ", dojo=" + dojo + ", graduacion=" + graduacion + ", historial=" + historial + "]";
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long pk) {
		this.id = pk;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public LocalDate getFechaIng() {
		return fechaIng;
	}

	public void setFechaIng(LocalDate fechaIng) {
		this.fechaIng = fechaIng;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDojo() {
		return dojo;
	}

	public void setDojo(String dojo) {
		this.dojo = dojo;
	}

	public String getGraduacion() {
		return graduacion;
	}

	public void setGraduacion(String graduacion) {
		this.graduacion = graduacion;
	}

	public Collection<HistorialExamen> getHistorial() {
		return historial;
	}

	public void setHistorial(Collection<HistorialExamen> historial) {
		this.historial = historial;
	}

}
