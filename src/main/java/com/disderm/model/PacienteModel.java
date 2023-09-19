package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class PacienteModel { 

	private Long id;

	private Long identificador_pac;

	private String dni;

	private String nombre;

	private String apellidos;

	private String telefono;

	private String correo;

	private Date fecha_nacimiento;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public Long get_identificador_pac() {
		 return this.identificador_pac;
	}
	public void set_identificador_pac(Long _identificador_pac) {
		 this.identificador_pac = _identificador_pac;
	}
	public String get_dni() {
		 return this.dni;
	}
	public void set_dni(String _dni) {
		 this.dni = _dni;
	}
	public String get_nombre() {
		 return this.nombre;
	}
	public void set_nombre(String _nombre) {
		 this.nombre = _nombre;
	}
	public String get_apellidos() {
		 return this.apellidos;
	}
	public void set_apellidos(String _apellidos) {
		 this.apellidos = _apellidos;
	}
	public String get_telefono() {
		 return this.telefono;
	}
	public void set_telefono(String _telefono) {
		 this.telefono = _telefono;
	}
	public String get_correo() {
		 return this.correo;
	}
	public void set_correo(String _correo) {
		 this.correo = _correo;
	}
	public Date get_fecha_nacimiento() {
		 return this.fecha_nacimiento;
	}
	public void set_fecha_nacimiento(Date _fecha_nacimiento) {
		 this.fecha_nacimiento = _fecha_nacimiento;
	}
}