package com.nelsonrueda.mercadolibreapp.Entities.Models;

public class ChildrenCategory extends Category{
    private int total_items_in_this_category;
    public ChildrenCategory(){}

    public int getTotal_items_in_this_category() {
        return total_items_in_this_category;
    }

    public void setTotal_items_in_this_category(int total_items_in_this_category) {
        this.total_items_in_this_category = total_items_in_this_category;
    }
}
