package com.disderm.model;

public class StockactualModel {

    private Long id;
    private int units;
    private int pharmacy_id;


    public Long get_id() {
        return this.id;
    }

    public void set_id(Long _id) {
        this.id = _id;
    }

    public int get_units() {
        return this.units;
    }

    public void set_units(int _units) {
        this.units = _units;
    }

    public int get_pharmacy_id() {
        return this.pharmacy_id;
    }

    public void set_pharmacy_id(int _pharmacy_id) {
        this.pharmacy_id = _pharmacy_id;
    }
}