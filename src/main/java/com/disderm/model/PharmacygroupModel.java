package com.disderm.model;

public class PharmacygroupModel {

    private Long id;
    private int pharmacy_id;
    private int pharmacist_id;


    public Long get_id() {
        return this.id;
    }

    public void set_id(Long _id) {
        this.id = _id;
    }

    public int get_pharmacy_id() {
        return this.pharmacy_id;
    }

    public void set_pharmacy_id(int _pharmacy_id) {
        this.pharmacy_id = _pharmacy_id;
    }

    public int get_pharmacist_id() {
        return this.pharmacist_id;
    }

    public void set_pharmacist_id(int _pharmacist_id) {
        this.pharmacist_id = _pharmacist_id;
    }
}