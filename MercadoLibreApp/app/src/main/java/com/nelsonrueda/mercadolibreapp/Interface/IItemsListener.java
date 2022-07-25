package com.nelsonrueda.mercadolibreapp.Interface;


import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByCategoryResult;
import com.nelsonrueda.mercadolibreapp.Entities.Models.ItemsByDataCustomResult;

/**
 * Intefaz para comunicar las informacion obtenidad de las peticiones API de busqueda de Items
 */
public interface IItemsListener {
    /**
     *Notificar la informacion de los item por categoria
     * @param result
     */
    void GetItemByCategory(ItemsByCategoryResult result);

    /**
     *Notificar la informacion de los item por busqueda personalidad
     * @param result
     */
    void GetItemByCustomResult(ItemsByDataCustomResult result);
}
