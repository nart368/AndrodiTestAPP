package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;

public class Settings {
    private boolean adult_content;
    private boolean buying_allowed;
    private ArrayList<String> buying_modes;
    private String catalog_domain;
    private String coverage_areas;
    private ArrayList<String> currencies;
    private boolean fragile;
    private String immediate_payment;
    private ArrayList<String> item_conditions;
    private boolean items_reviews_allowed;
    private boolean listing_allowed;
    private int max_description_length;
    private int max_pictures_per_item;
    private int max_pictures_per_item_var;
    private int max_sub_title_length;
    private int max_title_length;
    private int max_variations_allowed;
    private Object maximum_price;
    private String maximum_price_currency;
    private int minimum_price;
    private String minimum_price_currency;
    private Object mirror_category;
    private Object mirror_master_category;
    private ArrayList<Object> mirror_slave_categories;
    private String price;
    private String reservation_allowed;
    private ArrayList<Object> restrictions;
    private boolean rounded_address;
    private String seller_contact;
    private ArrayList<String> shipping_options;
    private String shipping_profile;
    private boolean show_contact_information;
    private String simple_shipping;
    private String stock;
    private Object sub_vertical;
    private boolean subscribable;
    private ArrayList<Object> tags;
    private Object vertical;
    private String vip_subdomain;
    private ArrayList<String> buyer_protection_programs;
    private String status;

    public Settings(){}

    public boolean isAdult_content() {
        return adult_content;
    }

    public void setAdult_content(boolean adult_content) {
        this.adult_content = adult_content;
    }

    public boolean isBuying_allowed() {
        return buying_allowed;
    }

    public void setBuying_allowed(boolean buying_allowed) {
        this.buying_allowed = buying_allowed;
    }

    public ArrayList<String> getBuying_modes() {
        return buying_modes;
    }

    public void setBuying_modes(ArrayList<String> buying_modes) {
        this.buying_modes = buying_modes;
    }

    public String getCatalog_domain() {
        return catalog_domain;
    }

    public void setCatalog_domain(String catalog_domain) {
        this.catalog_domain = catalog_domain;
    }

    public String getCoverage_areas() {
        return coverage_areas;
    }

    public void setCoverage_areas(String coverage_areas) {
        this.coverage_areas = coverage_areas;
    }

    public ArrayList<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<String> currencies) {
        this.currencies = currencies;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public String getImmediate_payment() {
        return immediate_payment;
    }

    public void setImmediate_payment(String immediate_payment) {
        this.immediate_payment = immediate_payment;
    }

    public ArrayList<String> getItem_conditions() {
        return item_conditions;
    }

    public void setItem_conditions(ArrayList<String> item_conditions) {
        this.item_conditions = item_conditions;
    }

    public boolean isItems_reviews_allowed() {
        return items_reviews_allowed;
    }

    public void setItems_reviews_allowed(boolean items_reviews_allowed) {
        this.items_reviews_allowed = items_reviews_allowed;
    }

    public boolean isListing_allowed() {
        return listing_allowed;
    }

    public void setListing_allowed(boolean listing_allowed) {
        this.listing_allowed = listing_allowed;
    }

    public int getMax_description_length() {
        return max_description_length;
    }

    public void setMax_description_length(int max_description_length) {
        this.max_description_length = max_description_length;
    }

    public int getMax_pictures_per_item() {
        return max_pictures_per_item;
    }

    public void setMax_pictures_per_item(int max_pictures_per_item) {
        this.max_pictures_per_item = max_pictures_per_item;
    }

    public int getMax_pictures_per_item_var() {
        return max_pictures_per_item_var;
    }

    public void setMax_pictures_per_item_var(int max_pictures_per_item_var) {
        this.max_pictures_per_item_var = max_pictures_per_item_var;
    }

    public int getMax_sub_title_length() {
        return max_sub_title_length;
    }

    public void setMax_sub_title_length(int max_sub_title_length) {
        this.max_sub_title_length = max_sub_title_length;
    }

    public int getMax_title_length() {
        return max_title_length;
    }

    public void setMax_title_length(int max_title_length) {
        this.max_title_length = max_title_length;
    }

    public int getMax_variations_allowed() {
        return max_variations_allowed;
    }

    public void setMax_variations_allowed(int max_variations_allowed) {
        this.max_variations_allowed = max_variations_allowed;
    }

    public Object getMaximum_price() {
        return maximum_price;
    }

    public void setMaximum_price(Object maximum_price) {
        this.maximum_price = maximum_price;
    }

    public String getMaximum_price_currency() {
        return maximum_price_currency;
    }

    public void setMaximum_price_currency(String maximum_price_currency) {
        this.maximum_price_currency = maximum_price_currency;
    }

    public int getMinimum_price() {
        return minimum_price;
    }

    public void setMinimum_price(int minimum_price) {
        this.minimum_price = minimum_price;
    }

    public String getMinimum_price_currency() {
        return minimum_price_currency;
    }

    public void setMinimum_price_currency(String minimum_price_currency) {
        this.minimum_price_currency = minimum_price_currency;
    }

    public Object getMirror_category() {
        return mirror_category;
    }

    public void setMirror_category(Object mirror_category) {
        this.mirror_category = mirror_category;
    }

    public Object getMirror_master_category() {
        return mirror_master_category;
    }

    public void setMirror_master_category(Object mirror_master_category) {
        this.mirror_master_category = mirror_master_category;
    }

    public ArrayList<Object> getMirror_slave_categories() {
        return mirror_slave_categories;
    }

    public void setMirror_slave_categories(ArrayList<Object> mirror_slave_categories) {
        this.mirror_slave_categories = mirror_slave_categories;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReservation_allowed() {
        return reservation_allowed;
    }

    public void setReservation_allowed(String reservation_allowed) {
        this.reservation_allowed = reservation_allowed;
    }

    public ArrayList<Object> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(ArrayList<Object> restrictions) {
        this.restrictions = restrictions;
    }

    public boolean isRounded_address() {
        return rounded_address;
    }

    public void setRounded_address(boolean rounded_address) {
        this.rounded_address = rounded_address;
    }

    public String getSeller_contact() {
        return seller_contact;
    }

    public void setSeller_contact(String seller_contact) {
        this.seller_contact = seller_contact;
    }

    public ArrayList<String> getShipping_options() {
        return shipping_options;
    }

    public void setShipping_options(ArrayList<String> shipping_options) {
        this.shipping_options = shipping_options;
    }

    public String getShipping_profile() {
        return shipping_profile;
    }

    public void setShipping_profile(String shipping_profile) {
        this.shipping_profile = shipping_profile;
    }

    public boolean isShow_contact_information() {
        return show_contact_information;
    }

    public void setShow_contact_information(boolean show_contact_information) {
        this.show_contact_information = show_contact_information;
    }

    public String getSimple_shipping() {
        return simple_shipping;
    }

    public void setSimple_shipping(String simple_shipping) {
        this.simple_shipping = simple_shipping;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Object getSub_vertical() {
        return sub_vertical;
    }

    public void setSub_vertical(Object sub_vertical) {
        this.sub_vertical = sub_vertical;
    }

    public boolean isSubscribable() {
        return subscribable;
    }

    public void setSubscribable(boolean subscribable) {
        this.subscribable = subscribable;
    }

    public ArrayList<Object> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Object> tags) {
        this.tags = tags;
    }

    public Object getVertical() {
        return vertical;
    }

    public void setVertical(Object vertical) {
        this.vertical = vertical;
    }

    public String getVip_subdomain() {
        return vip_subdomain;
    }

    public void setVip_subdomain(String vip_subdomain) {
        this.vip_subdomain = vip_subdomain;
    }

    public ArrayList<String> getBuyer_protection_programs() {
        return buyer_protection_programs;
    }

    public void setBuyer_protection_programs(ArrayList<String> buyer_protection_programs) {
        this.buyer_protection_programs = buyer_protection_programs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
