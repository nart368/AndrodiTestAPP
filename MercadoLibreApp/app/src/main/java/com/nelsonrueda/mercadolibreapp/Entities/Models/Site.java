package com.nelsonrueda.mercadolibreapp.Entities.Models;

public class Site {
    private String default_currency_id;
    private String id;
    private String name;

    public Site(){}

    public Site(String defaultCurrendyId, String id, String name){
        this.setDefault_currency_id(defaultCurrendyId);
        this.setId(id);
        this.setName(name);
    }

    public String getDefault_currency_id() {
        return default_currency_id;
    }

    public void setDefault_currency_id(String default_currency_id) {
        this.default_currency_id = default_currency_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
