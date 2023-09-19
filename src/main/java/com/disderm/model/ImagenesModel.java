package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class ImagenesModel { 

	private Long id;

	private Long diagnostico_id;

	private String imagen_nombre;

	private Date imagen_datetime;

	private String imagen;

	private String localizacion;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public Long get_diagnostico_id() {
		 return this.diagnostico_id;
	}
	public void set_diagnostico_id(Long _diagnostico_id) {
		 this.diagnostico_id = _diagnostico_id;
	}
	public String get_imagen_nombre() {
		 return this.imagen_nombre;
	}
	public void set_imagen_nombre(String _imagen_nombre) {
		 this.imagen_nombre = _imagen_nombre;
	}
	public Date get_imagen_datetime() {
		 return this.imagen_datetime;
	}
	public void set_imagen_datetime(Date _imagen_datetime) {
		 this.imagen_datetime = _imagen_datetime;
	}
	public String get_imagen() {
		 return this.imagen;
	}
	public void set_imagen(String _imagen) {
		 this.imagen = _imagen;
	}
	public String get_localizacion() {
		 return this.localizacion;
	}
	public void set_localizacion(String _localizacion) {
		 this.localizacion = _localizacion;
	}
}