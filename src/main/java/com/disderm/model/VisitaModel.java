package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class VisitaModel { 

	private Long id;

	private Date fecha_visita;

	private String urgencia;

	private String sintomatologia;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public Date get_fecha_visita() {
		 return this.fecha_visita;
	}
	public void set_fecha_visita(Date _fecha_visita) {
		 this.fecha_visita = _fecha_visita;
	}
	public String get_urgencia() {
		 return this.urgencia;
	}
	public void set_urgencia(String _urgencia) {
		 this.urgencia = _urgencia;
	}
	public String get_sintomatologia() {
		 return this.sintomatologia;
	}
	public void set_sintomatologia(String _sintomatologia) {
		 this.sintomatologia = _sintomatologia;
	}
}