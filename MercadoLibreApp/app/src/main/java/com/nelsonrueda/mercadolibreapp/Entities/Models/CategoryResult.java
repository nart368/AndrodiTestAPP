package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;

public class CategoryResult {
    private ArrayList<Category> categories;

    public CategoryResult(){}

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
