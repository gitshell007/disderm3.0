package com.disderm.model;

public class ProductModel {

    private Long id;
    private int category_id;
    private String description;
    private int import_source_id;
    private int cn;


    public Long get_id() {
        return this.id;
    }

    public void set_id(Long _id) {
        this.id = _id;
    }

    public int get_category_id() {
        return this.category_id;
    }

    public void set_category_id(int _category_id) {
        this.category_id = _category_id;
    }

    public String get_description() {
        return this.description;
    }

    public void set_description(String _description) {
        this.description = _description;
    }

    public int get_import_source_id() {
        return this.import_source_id;
    }

    public void set_import_source_id(int _import_source_id) {
        this.import_source_id = _import_source_id;
    }

    public int get_cn() {
        return this.cn;
    }

    public void set_cn(int _cn) {
        this.cn = _cn;
    }
}