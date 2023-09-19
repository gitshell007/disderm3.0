package com.disderm.model;

public class UsertypeModel {

	private Long user_type_id;
	private String id;
	private String user_type_des;
	private String user_type_code;
	public Long get_user_type_id() { return this.user_type_id; }
	public String get_id() { return this.id; }
	public void set_user_type_id(Long _user_type_id ) { this.user_type_id = _user_type_id; }
	public void set_id(String _id ) { this.id = _id; }

	public String get_user_type_des() { return this.user_type_des; }
	public void set_user_type_des(String _user_type_des ) { this.user_type_des = _user_type_des; }

	public String get_user_type_code() { return this.user_type_code; }
	public void set_user_type_code(String _user_type_code ) { this.user_type_code = _user_type_code; }
}