package com.disderm.model;
import java.util.Date;
import java.sql.Time;
public class VisitaimageModel { 

	private Long id;

	private Long visita_id;

	private String image;

	public Long get_id() {
		 return id; 
	}
	public void set_id(Long _id) {
		 this.id = _id; 
	}
	public Long get_visita_id() {
		 return this.visita_id;
	}
	public void set_visita_id(Long _visita_id) {
		 this.visita_id = _visita_id;
	}
	public String get_image() {
		 return this.image;
	}
	public void set_image(String _image) {
		 this.image = _image;
	}
}