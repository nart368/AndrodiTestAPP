package com.nelsonrueda.mercadolibreapp.Entities.Models;

public class Seller {
    private int id;
    private Object permalink;
    private Object registration_date;
    private boolean car_dealer;
    private boolean real_estate_agency;
    private Object tags;

    public Seller(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getPermalink() {
        return permalink;
    }

    public void setPermalink(Object permalink) {
        this.permalink = permalink;
    }

    public Object getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Object registration_date) {
        this.registration_date = registration_date;
    }

    public boolean isCar_dealer() {
        return car_dealer;
    }

    public void setCar_dealer(boolean car_dealer) {
        this.car_dealer = car_dealer;
    }

    public boolean isReal_estate_agency() {
        return real_estate_agency;
    }

    public void setReal_estate_agency(boolean real_estate_agency) {
        this.real_estate_agency = real_estate_agency;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }
}
