package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;

public class Value {
    private String id;
    private String name;
    private Struct struct;
    private Object source;
    private ArrayList<PathFromRoot> path_from_root;
    private int results;

    public Value(){}

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

    public Struct getStruct() {
        return struct;
    }

    public void setStruct(Struct struct) {
        this.struct = struct;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public ArrayList<PathFromRoot> getPath_from_root() {
        return path_from_root;
    }

    public void setPath_from_root(ArrayList<PathFromRoot> path_from_root) {
        this.path_from_root = path_from_root;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
