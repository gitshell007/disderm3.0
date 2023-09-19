package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class UsuariosModel { 

	private Long id;

	private String nombre_usuario;

	private String password;

	private String nombre;

	private String apellido;

	private String nombre_mostrar;

	private Long perfil;

	private String imagen_perfil;

	private Long habilitado;

	private String telefono;

	private String localidad;

	private String provincia;

	private String cp;

	private String app_platform;

	private String app_device_model;

	private String app_device_token;

	private Date fecha_alta_sistema;

	private Date fecha_ultimo_acceso;

	private String mensaje;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public String get_nombre_usuario() {
		 return this.nombre_usuario;
	}
	public void set_nombre_usuario(String _nombre_usuario) {
		 this.nombre_usuario = _nombre_usuario;
	}
	public String get_password() {
		 return this.password;
	}
	public void set_password(String _password) {
		 this.password = _password;
	}
	public String get_nombre() {
		 return this.nombre;
	}
	public void set_nombre(String _nombre) {
		 this.nombre = _nombre;
	}
	public String get_apellido() {
		 return this.apellido;
	}
	public void set_apellido(String _apellido) {
		 this.apellido = _apellido;
	}
	public String get_nombre_mostrar() {
		 return this.nombre_mostrar;
	}
	public void set_nombre_mostrar(String _nombre_mostrar) {
		 this.nombre_mostrar = _nombre_mostrar;
	}
	public Long get_perfil() {
		 return this.perfil;
	}
	public void set_perfil(Long _perfil) {
		 this.perfil = _perfil;
	}
	public String get_imagen_perfil() {
		 return this.imagen_perfil;
	}
	public void set_imagen_perfil(String _imagen_perfil) {
		 this.imagen_perfil = _imagen_perfil;
	}
	public Long get_habilitado() {
		 return this.habilitado;
	}
	public void set_habilitado(Long _habilitado) {
		 this.habilitado = _habilitado;
	}
	public String get_telefono() {
		 return this.telefono;
	}
	public void set_telefono(String _telefono) {
		 this.telefono = _telefono;
	}
	public String get_localidad() {
		 return this.localidad;
	}
	public void set_localidad(String _localidad) {
		 this.localidad = _localidad;
	}
	public String get_provincia() {
		 return this.provincia;
	}
	public void set_provincia(String _provincia) {
		 this.provincia = _provincia;
	}
	public String get_cp() {
		 return this.cp;
	}
	public void set_cp(String _cp) {
		 this.cp = _cp;
	}
	public String get_app_platform() {
		 return this.app_platform;
	}
	public void set_app_platform(String _app_platform) {
		 this.app_platform = _app_platform;
	}
	public String get_app_device_model() {
		 return this.app_device_model;
	}
	public void set_app_device_model(String _app_device_model) {
		 this.app_device_model = _app_device_model;
	}
	public String get_app_device_token() {
		 return this.app_device_token;
	}
	public void set_app_device_token(String _app_device_token) {
		 this.app_device_token = _app_device_token;
	}
	public Date get_fecha_alta_sistema() {
		 return this.fecha_alta_sistema;
	}
	public void set_fecha_alta_sistema(Date _fecha_alta_sistema) {
		 this.fecha_alta_sistema = _fecha_alta_sistema;
	}
	public Date get_fecha_ultimo_acceso() {
		 return this.fecha_ultimo_acceso;
	}
	public void set_fecha_ultimo_acceso(Date _fecha_ultimo_acceso) {
		 this.fecha_ultimo_acceso = _fecha_ultimo_acceso;
	}
	public String get_mensaje() {
		 return this.mensaje;
	}
	public void set_mensaje(String _mensaje) {
		 this.mensaje = _mensaje;
	}
}