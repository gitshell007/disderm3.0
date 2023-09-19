package com.disderm.model;

public class LogsModel {

	private Long logs_id;
	private String id;
	private String type;
	private String date;
	private String user;
	private String des;
	public Long get_logs_id() { return this.logs_id; }
	public String get_id() { return this.id; }
	public void set_logs_id(Long _logs_id ) { this.logs_id = _logs_id; }
	public void set_id(String _id ) { this.id = _id; }

	public String get_type() { return this.type; }
	public void set_type(String _type ) { this.type = _type; }

	public String get_date() { return this.date; }
	public void set_date(String _date ) { this.date = _date; }

	public String get_user() { return this.user; }
	public void set_user(String _user ) { this.user = _user; }

	public String get_des() { return this.des; }
	public void set_des(String _des ) { this.des = _des; }
}