package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;

public class ItemsByDataCustomResult {
    private String site_id;
    private String country_default_time_zone;
    private String query;
    private Paging paging;
    private ArrayList<Result> results;
    private Sort sort;
    private ArrayList<AvailableSort> available_sorts;
    private ArrayList<Filter> filters;
    private ArrayList<AvailableFilter> available_filters;

    public ItemsByDataCustomResult(){}

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getCountry_default_time_zone() {
        return country_default_time_zone;
    }

    public void setCountry_default_time_zone(String country_default_time_zone) {
        this.country_default_time_zone = country_default_time_zone;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public ArrayList<AvailableSort> getAvailable_sorts() {
        return available_sorts;
    }

    public void setAvailable_sorts(ArrayList<AvailableSort> available_sorts) {
        this.available_sorts = available_sorts;
    }

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Filter> filters) {
        this.filters = filters;
    }

    public ArrayList<AvailableFilter> getAvailable_filters() {
        return available_filters;
    }

    public void setAvailable_filters(ArrayList<AvailableFilter> available_filters) {
        this.available_filters = available_filters;
    }
}
