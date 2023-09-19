package com.disderm.model;

import java.math.BigDecimal;
import java.util.Date;

public class SalesModel {

    private Long id;
    private String description;
    private int pharmacy_id;
    private int product_id;
    private Date upload_date;
    private Date sale_date;
    private int units_number;
    private BigDecimal units_price;
    private int import_source_id;
    private int payment_type_id;
    private int sale_type_id;


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

    public int get_product_id() {
        return this.product_id;
    }

    public void set_product_id(int _product_id) {
        this.product_id = _product_id;
    }

    public Date get_upload_date() {
        return this.upload_date;
    }

    public void set_upload_date(Date _upload_date) {
        this.upload_date = _upload_date;
    }

    public Date get_sale_date() {
        return this.sale_date;
    }

    public void set_sale_date(Date _sale_date) {
        this.sale_date = _sale_date;
    }

    public int get_units_number() {
        return this.units_number;
    }

    public void set_units_number(int _units_number) {
        this.units_number = _units_number;
    }

    public BigDecimal get_units_price() {
        return this.units_price;
    }

    public void set_units_price(BigDecimal _units_price) {
        this.units_price = _units_price;
    }

    public int get_import_source_id() {
        return this.import_source_id;
    }

    public void set_import_source_id(int _import_source_id) {
        this.import_source_id = _import_source_id;
    }

    public int get_payment_type_id() {
        return this.payment_type_id;
    }

    public void set_payment_type_id(int _payment_type_id) {
        this.payment_type_id = _payment_type_id;
    }

    public int get_sale_type_id() {
        return this.sale_type_id;
    }

    public void set_sale_type_id(int _sale_type_id) {
        this.sale_type_id = _sale_type_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}