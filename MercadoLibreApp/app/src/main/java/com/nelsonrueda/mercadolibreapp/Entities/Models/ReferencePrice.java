package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;
import java.util.Date;

public class ReferencePrice {
    private String id;
    private String type;
    private Conditions conditions;
    private int amount;
    private String currency_id;
    private String exchange_rate_context;
    private ArrayList<Object> tags;
    private Date last_updated;

    public ReferencePrice(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getExchange_rate_context() {
        return exchange_rate_context;
    }

    public void setExchange_rate_context(String exchange_rate_context) {
        this.exchange_rate_context = exchange_rate_context;
    }

    public ArrayList<Object> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Object> tags) {
        this.tags = tags;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }
}
