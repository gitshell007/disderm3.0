package com.disderm.model;
import java.util.Date;
public class UploadModel { 

	private Long upload_id;
	private String id;
	private String type;
	private String name;
	private String status;
	private Date upload_date;
	public Long get_upload_id() { return this.upload_id; }
	public String get_id() { return this.id; }
	public void set_upload_id(Long _upload_id ) { this.upload_id = _upload_id; }
	public void set_id(String _id ) { this.id = _id; }

	public String get_type() { return this.type; }
	public void set_type(String _type ) { this.type = _type; }

	public String get_name() { return this.name; }
	public void set_name(String _name ) { this.name = _name; }

	public String get_status() { return this.status; }
	public void set_status(String _status ) { this.status = _status; }

	public Date get_upload_date() { return this.upload_date; }
	public void set_upload_date(Date _upload_date ) { this.upload_date = _upload_date; }
}