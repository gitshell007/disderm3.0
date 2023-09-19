package com.disderm.model;

/**
 * User: epajaron
 * Date: 25/02/14
 * Time: 7:12
 */
public class SystemModel {

    private Long id_system;
    private String service;
    private String field;
    private String value;

    public Long getId_system() {
        return id_system;
    }

    public void setId_system(Long id_system) {
        this.id_system = id_system;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SystemModel() {
    }

    public SystemModel(String service, String field, String value) {
        this.service = service;
        this.field = field;
        this.value = value;
    }
}
