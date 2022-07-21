package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;

public class AvailableFilter {
    private String id;
    private String name;
    private String type;
    private ArrayList<Value> values;

    public AvailableFilter(){}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Value> getValues() {
        return values;
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }
}
