package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class MensajesincidenciaModel { 

	private Long id;

	private Date fecha;

	private String tipo_incidencia;

	private String mensaje;

	private Long diagnostico_incidencia;

	private Long facultativo;

	private Long administrador;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public Date get_fecha() {
		 return this.fecha;
	}
	public void set_fecha(Date _fecha) {
		 this.fecha = _fecha;
	}
	public String get_tipo_incidencia() {
		 return this.tipo_incidencia;
	}
	public void set_tipo_incidencia(String _tipo_incidencia) {
		 this.tipo_incidencia = _tipo_incidencia;
	}
	public String get_mensaje() {
		 return this.mensaje;
	}
	public void set_mensaje(String _mensaje) {
		 this.mensaje = _mensaje;
	}
	public Long get_diagnostico_incidencia() {
		 return this.diagnostico_incidencia;
	}
	public void set_diagnostico_incidencia(Long _diagnostico_incidencia) {
		 this.diagnostico_incidencia = _diagnostico_incidencia;
	}
	public Long get_facultativo() {
		 return this.facultativo;
	}
	public void set_facultativo(Long _facultativo) {
		 this.facultativo = _facultativo;
	}
	public Long get_administrador() {
		 return this.administrador;
	}
	public void set_administrador(Long _administrador) {
		 this.administrador = _administrador;
	}
}