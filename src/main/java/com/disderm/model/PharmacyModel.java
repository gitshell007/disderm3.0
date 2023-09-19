package com.disderm.model;

public class PharmacyModel {

    private Long id;
    private String description;


    public Long get_id() {
        return this.id;
    }

    public void set_id(Long _id) {
        this.id = _id;
    }

    public String get_description() {
        return this.description;
    }

    public void set_description(String _description) {
        this.description = _description;
    }
}