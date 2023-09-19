package com.disderm.model;

import java.util.Date;

public class DisdermusersModel {

    private Long id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String visible_name;
    private int user_type_id;
    private String profile_image;
    private int enabled;
    private String phone_mobile;
    private String localidad;
    private String provincia;
    private String cp;
    private String app_platform;
    private String app_device_model;
    private String app_device_token;
    private Date fecha_alta_sistema;
    private Date fecha_ultimo_acceso;
    private String message;



    public Long get_id() {
        return this.id;
    }

    public void set_id(Long _id) {
        this.id = _id;
    }

    public String get_username() {
        return this.username;
    }

    public void set_username(String _username) {
        this.username = _username;
    }

    public String get_password() {
        return this.password;
    }

    public void set_password(String _password) {
        this.password = _password;
    }

    public String get_first_name() {
        return this.first_name;
    }

    public void set_first_name(String _first_name) {
        this.first_name = _first_name;
    }

    public String get_last_name() {
        return this.last_name;
    }

    public void set_last_name(String _last_name) {
        this.last_name = _last_name;
    }

    public String get_visible_name() {
        return this.visible_name;
    }

    public void set_visible_name(String _visible_name) {
        this.visible_name = _visible_name;
    }

    public int get_user_type_id() {
        return this.user_type_id;
    }

    public void set_user_type_id(int _user_type_id) {
        this.user_type_id = _user_type_id;
    }

    public String get_profile_image() {
        return this.profile_image;
    }

    public void set_profile_image(String _profile_image) {
        this.profile_image = _profile_image;
    }

    public int get_enabled() {
        return this.enabled;
    }

    public void set_enabled(int _enabled) {
        this.enabled = _enabled;
    }

    public String get_phone_mobile() {
        return this.phone_mobile;
    }

    public void set_phone_mobile(String _phone_mobile) {
        this.phone_mobile = _phone_mobile;
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

    public String get_message() {
        return this.message;
    }

    public void set_message(String _message) {
        this.message = _message;
    }

}