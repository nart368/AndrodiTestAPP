package com.nelsonrueda.mercadolibreapp.Interface;


import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByCategoryResult;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByDataCustomResult;

public interface IItemsListener {
    void GetItemByCategory(ItemsByCategoryResult result);
    void GetItemByCustomResult(ItemsByDataCustomResult result);
}
