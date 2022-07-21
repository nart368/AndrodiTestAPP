package com.nelsonrueda.mercadolibreapp.Entities.Utils;

public class Constants {
    public static final String BASE_URL_MERCADOLIBRE = "https://api.mercadolibre.com";
    public static final String API_GET_SITES = "/sites";
    public static final String API_GET_CATEGORIES = "/sites/$SITE_ID/categories";
    public static final String API_GET_SUBCATEGORIES = "/categories/$CATEGORY_ID";
    public static final String API_GET_ITEMS_BY_CATEGORY = "/sites/$SITE_ID/search?category=$CATEGORY_ID";
    public static final String API_GET_INFO_BY_CUSTOM_SEARCH = "/sites/$SITE_ID/search?q=$DATA";
}
