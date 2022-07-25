package com.nelsonrueda.mercadolibreapp.Entities.Enums;

/**
 * Enumerador para los tipos de peticion que se podran hacer al manejador de peticiones,
 * que ayudara a identificar a quien pertenece la peticion
 */
    public enum RequestType {
        REQUEST_SITES,
        REQUEST_CATEGORIES,
        REQUEST_SUBCATEGORIES,
        REQUEST_ITEMS_BY_CATEGORY,
        REQUEST_ITEMS_BY_CUSTOM_DATA
    }

