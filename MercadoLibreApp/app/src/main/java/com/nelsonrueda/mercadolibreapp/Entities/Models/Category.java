package com.nelsonrueda.mercadolibreapp.Entities.Models;

public class Category {
    protected String id;
    protected String name;

    public Category(){}

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
        return  this.name;
    }
}