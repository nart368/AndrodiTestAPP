package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;

public class Shipping {
    private boolean free_shipping;
    private String mode;
    private ArrayList<String> tags;
    private String logistic_type;
    private boolean store_pick_up;

    public Shipping(){}

    public boolean isFree_shipping() {
        return free_shipping;
    }

    public void setFree_shipping(boolean free_shipping) {
        this.free_shipping = free_shipping;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getLogistic_type() {
        return logistic_type;
    }

    public void setLogistic_type(String logistic_type) {
        this.logistic_type = logistic_type;
    }

    public boolean isStore_pick_up() {
        return store_pick_up;
    }

    public void setStore_pick_up(boolean store_pick_up) {
        this.store_pick_up = store_pick_up;
    }
}
