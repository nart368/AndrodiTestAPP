package com.nelsonrueda.mercadolibreapp.Interface;

import com.nelsonrueda.mercadolibreapp.Entities.Models.Category;
import com.nelsonrueda.mercadolibreapp.Entities.Models.SubCategoryResult;

import java.util.ArrayList;

/**
 * Intefaz para comunicar las informacion obtenidad de las peticiones API de categoria
 */
public interface ICategoriesListener {

    void GetCategories(ArrayList<Category> categoriesResult);
    //void GetSubCategories(SubCategoryResult subcategoriesResult);
}
