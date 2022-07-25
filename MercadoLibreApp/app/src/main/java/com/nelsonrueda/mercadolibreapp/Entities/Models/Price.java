package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;
import java.util.Date;

public class Price {
    private String id;
    private String type;
    private float amount;
    private float regular_amount;
    private String currency_id;
    private Date last_updated;
    private Conditions conditions;
    private String exchange_rate_context;
    private Metadata metadata;
    private ArrayList<Price> prices;
    private Presentation presentation;
    private ArrayList<Object> payment_method_prices;
    private ArrayList<ReferencePrice> reference_prices;
    private ArrayList<Object> purchase_discounts;

    public Price(){}

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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getRegular_amount() {
        return regular_amount;
    }

    public void setRegular_amount(float regular_amount) {
        this.regular_amount = regular_amount;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public String getExchange_rate_context() {
        return exchange_rate_context;
    }

    public void setExchange_rate_context(String exchange_rate_context) {
        this.exchange_rate_context = exchange_rate_context;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public ArrayList<Object> getPayment_method_prices() {
        return payment_method_prices;
    }

    public void setPayment_method_prices(ArrayList<Object> payment_method_prices) {
        this.payment_method_prices = payment_method_prices;
    }

    public ArrayList<ReferencePrice> getReference_prices() {
        return reference_prices;
    }

    public void setReference_prices(ArrayList<ReferencePrice> reference_prices) {
        this.reference_prices = reference_prices;
    }

    public ArrayList<Object> getPurchase_discounts() {
        return purchase_discounts;
    }

    public void setPurchase_discounts(ArrayList<Object> purchase_discounts) {
        this.purchase_discounts = purchase_discounts;
    }
}
