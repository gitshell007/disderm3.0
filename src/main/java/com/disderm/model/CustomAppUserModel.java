package com.disderm.model;

import java.util.Date;

public class CustomAppUserModel {

	private Long id;
	private String nombre_usuario;
	private String password;
	private String nombre;
	private String apellido;
	private String nombre_mostrar;
	private Long perfil;
	private String imagen_perfil;
	private boolean enabled;
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
	private boolean success;
	private String confirm_password;
	private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getNombre_mostrar() {
        return nombre_mostrar;
    }

    public void setNombre_mostrar(String nombre_mostrar) {
        this.nombre_mostrar = nombre_mostrar;
    }

    public Long getPerfil() {
        return perfil;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }

    public String getImagen_perfil() {
        return imagen_perfil;
    }

    public void setImagen_perfil(String imagen_perfil) {
        this.imagen_perfil = imagen_perfil;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getApp_platform() {
        return app_platform;
    }

    public void setApp_platform(String app_platform) {
        this.app_platform = app_platform;
    }

    public String getApp_device_model() {
        return app_device_model;
    }

    public void setApp_device_model(String app_device_model) {
        this.app_device_model = app_device_model;
    }

    public String getApp_device_token() {
        return app_device_token;
    }

    public void setApp_device_token(String app_device_token) {
        this.app_device_token = app_device_token;
    }

    public Date getFecha_alta_sistema() {
        return fecha_alta_sistema;
    }

    public void setFecha_alta_sistema(Date fecha_alta_sistema) {
        this.fecha_alta_sistema = fecha_alta_sistema;
    }

    public Date getFecha_ultimo_acceso() {
        return fecha_ultimo_acceso;
    }

    public void setFecha_ultimo_acceso(Date fecha_ultimo_acceso) {
        this.fecha_ultimo_acceso = fecha_ultimo_acceso;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean get_success() { return this.enabled; }

    public void set_success(boolean _success ) { this.enabled = _success; }
}