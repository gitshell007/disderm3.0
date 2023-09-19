package com.disderm.model;

import java.util.Date;

public class DiagnosticoPacienteModel {
    private Long id;

    private Long paciente_id;

    private Date fecha;

    private String urgente;

    private Long user_facultativo_id;

    private String estado;

    private Long user_admin_id;

    private Long identificador_pac;

    private String dni;

    private String nombre;

    private String apellidos;

    private Long telefono;

    private String correo;

    private Date fecha_nacimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public Long getUser_facultativo_id() {
        return user_facultativo_id;
    }

    public void setUser_facultativo_id(Long user_facultativo_id) {
        this.user_facultativo_id = user_facultativo_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUser_admin_id() {
        return user_admin_id;
    }

    public void setUser_admin_id(Long user_admin_id) {
        this.user_admin_id = user_admin_id;
    }

    public Long getIdentificador_pac() {
        return identificador_pac;
    }

    public void setIdentificador_pac(Long identificador_pac) {
        this.identificador_pac = identificador_pac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
