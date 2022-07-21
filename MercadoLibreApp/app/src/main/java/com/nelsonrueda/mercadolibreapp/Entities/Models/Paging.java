package com.nelsonrueda.mercadolibreapp.Entities.Models;

public class Paging {
    private int total;
    private int primary_results;
    private int offset;
    private int limit;

    public Paging(){}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrimary_results() {
        return primary_results;
    }

    public void setPrimary_results(int primary_results) {
        this.primary_results = primary_results;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
