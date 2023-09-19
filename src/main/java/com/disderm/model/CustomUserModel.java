package com.disderm.model;

public class CustomUserModel {

    private Long user_id;
    private String first_name;
    private String last_name;
    private int enabled;
    private String username;
    private String email;
    private String password;
    private String token;
    private String phone_mobile1;
    private String profile_image;
    private String message;
    private String device_token;
    private String security_token;
    public Long get_user_id() {
        return this.user_id;
    }

    public void set_user_id(Long _user_id) {
        this.user_id = _user_id;
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

    public int get_enabled() {
        return this.enabled;
    }


    public void set_enabled(int _enabled) {
        this.enabled = _enabled;
    }

    public String get_username() {
        return this.username;
    }

    public void set_username(String _username) {
        this.username = _username;
    }

    public String get_email() {
        return this.email;
    }

    public void set_email(String _email) {
        this.email = _email;
    }

    public String get_password() {
        return this.password;
    }

    public void set_password(String _password) {
        this.password = _password;
    }

    public String get_token() {
        return this.token;
    }

    public void set_token(String _token) {
        this.token = _token;
    }

    public String get_phone_mobile1() {
        return this.phone_mobile1;
    }

    public void set_phone_mobile1(String _phone_mobile1) {
        this.phone_mobile1 = _phone_mobile1;
    }

    public String get_profile_image() {
        return this.profile_image;
    }

    public void set_profile_image(String _profile_image) {
        this.profile_image = _profile_image;
    }

    public String get_message() {
        return this.message;
    }

    public void set_message(String _message) {
        this.message = _message;
    }

    public String get_device_token() {
        return this.device_token;
    }

    public void set_device_token(String _device_token) {
        this.device_token = _device_token;
    }

    public String get_security_token() {
        return this.device_token;
    }

    public void set_security_token(String _device_token) {
        this.device_token = _device_token;
    }

}