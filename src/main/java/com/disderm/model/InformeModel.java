package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class InformeModel { 

	private Long id;

	private String situacion_lesion;

	private String informe;

	private String tipo_incidencia;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public String get_situacion_lesion() {
		 return this.situacion_lesion;
	}
	public void set_situacion_lesion(String _situacion_lesion) {
		 this.situacion_lesion = _situacion_lesion;
	}
	public String get_informe() {
		 return this.informe;
	}
	public void set_informe(String _informe) {
		 this.informe = _informe;
	}
	public String get_tipo_incidencia() {
		 return this.tipo_incidencia;
	}
	public void set_tipo_incidencia(String _tipo_incidencia) {
		 this.tipo_incidencia = _tipo_incidencia;
	}
}