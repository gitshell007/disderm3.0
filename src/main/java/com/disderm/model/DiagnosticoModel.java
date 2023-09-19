package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class DiagnosticoModel { 

	private Long id;

	private Long paciente_id;

	private Date fecha;

	private String patologia;

	private String urgente;

	private String observaciones;

	private String estado;

	private Long user_facultativo_id;

	private Long user_admin_id;

	private Long user_primaria_id;

	private String valoracion_dermatologo;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public Long get_paciente_id() {
		 return this.paciente_id;
	}
	public void set_paciente_id(Long _paciente_id) {
		 this.paciente_id = _paciente_id;
	}
	public Date get_fecha() {
		 return this.fecha;
	}
	public void set_fecha(Date _fecha) {
		 this.fecha = _fecha;
	}
	public String get_patologia() {
		 return this.patologia;
	}
	public void set_patologia(String _patologia) {
		 this.patologia = _patologia;
	}
	public String get_urgente() {
		 return this.urgente;
	}
	public void set_urgente(String _urgente) {
		 this.urgente = _urgente;
	}
	public String get_observaciones() {
		 return this.observaciones;
	}
	public void set_observaciones(String _observaciones) {
		 this.observaciones = _observaciones;
	}
	public String get_estado() {
		 return this.estado;
	}
	public void set_estado(String _estado) {
		 this.estado = _estado;
	}
	public Long get_user_facultativo_id() {
		 return this.user_facultativo_id;
	}
	public void set_user_facultativo_id(Long _user_facultativo_id) {
		 this.user_facultativo_id = _user_facultativo_id;
	}
	public Long get_user_admin_id() {
		 return this.user_admin_id;
	}
	public void set_user_admin_id(Long _user_admin_id) {
		 this.user_admin_id = _user_admin_id;
	}
	public Long get_user_primaria_id() {
		 return this.user_primaria_id;
	}
	public void set_user_primaria_id(Long _user_primaria_id) {
		 this.user_primaria_id = _user_primaria_id;
	}
	public String get_valoracion_dermatologo() {
		 return this.valoracion_dermatologo;
	}
	public void set_valoracion_dermatologo(String _valoracion_dermatologo) {
		 this.valoracion_dermatologo = _valoracion_dermatologo;
	}
}