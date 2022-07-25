package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;
import java.util.Date;

public class Result {
    private String id;
    private String site_id;
    private String title;
    private Seller seller;
    private int price;
    private Price prices;
    private Object sale_price;
    private String currency_id;
    private int available_quantity;
    private int sold_quantity;
    private String buying_mode;
    private String listing_type_id;
    private Date stop_time;
    private String condition;
    private String permalink;
    private String thumbnail;
    private String thumbnail_id;
    private boolean accepts_mercadopago;
    private Installments installments;
    private Address address;
    private Shipping shipping;
    private SellerAddress seller_address;
    private ArrayList<Attribute> attributes;
    private int original_price;
    private String category_id;
    private int official_store_id;
    private String domain_id;
    private String catalog_product_id;
    private ArrayList<String> tags;
    private int order_backend;
    private boolean use_thumbnail_id;
    private Object offer_score;
    private Object offer_share;
    private Object match_score;
    private Object winner_item_id;
    private Object melicoin;
    private Object discounts;
    private DifferentialPricing differential_pricing;
    private boolean catalog_listing;

    public Result(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Price getPrices() {
        return prices;
    }

    public void setPrices(Price prices) {
        this.prices = prices;
    }

    public Object getSale_price() {
        return sale_price;
    }

    public void setSale_price(Object sale_price) {
        this.sale_price = sale_price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public int getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(int sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public String getBuying_mode() {
        return buying_mode;
    }

    public void setBuying_mode(String buying_mode) {
        this.buying_mode = buying_mode;
    }

    public String getListing_type_id() {
        return listing_type_id;
    }

    public void setListing_type_id(String listing_type_id) {
        this.listing_type_id = listing_type_id;
    }

    public Date getStop_time() {
        return stop_time;
    }

    public void setStop_time(Date stop_time) {
        this.stop_time = stop_time;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail_id() {
        return thumbnail_id;
    }

    public void setThumbnail_id(String thumbnail_id) {
        this.thumbnail_id = thumbnail_id;
    }

    public boolean isAccepts_mercadopago() {
        return accepts_mercadopago;
    }

    public void setAccepts_mercadopago(boolean accepts_mercadopago) {
        this.accepts_mercadopago = accepts_mercadopago;
    }

    public Installments getInstallments() {
        return installments;
    }

    public void setInstallments(Installments installments) {
        this.installments = installments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public SellerAddress getSeller_address() {
        return seller_address;
    }

    public void setSeller_address(SellerAddress seller_address) {
        this.seller_address = seller_address;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getOfficial_store_id() {
        return official_store_id;
    }

    public void setOfficial_store_id(int official_store_id) {
        this.official_store_id = official_store_id;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getCatalog_product_id() {
        return catalog_product_id;
    }

    public void setCatalog_product_id(String catalog_product_id) {
        this.catalog_product_id = catalog_product_id;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getOrder_backend() {
        return order_backend;
    }

    public void setOrder_backend(int order_backend) {
        this.order_backend = order_backend;
    }

    public boolean isUse_thumbnail_id() {
        return use_thumbnail_id;
    }

    public void setUse_thumbnail_id(boolean use_thumbnail_id) {
        this.use_thumbnail_id = use_thumbnail_id;
    }

    public Object getOffer_score() {
        return offer_score;
    }

    public void setOffer_score(Object offer_score) {
        this.offer_score = offer_score;
    }

    public Object getOffer_share() {
        return offer_share;
    }

    public void setOffer_share(Object offer_share) {
        this.offer_share = offer_share;
    }

    public Object getMatch_score() {
        return match_score;
    }

    public void setMatch_score(Object match_score) {
        this.match_score = match_score;
    }

    public Object getWinner_item_id() {
        return winner_item_id;
    }

    public void setWinner_item_id(Object winner_item_id) {
        this.winner_item_id = winner_item_id;
    }

    public Object getMelicoin() {
        return melicoin;
    }

    public void setMelicoin(Object melicoin) {
        this.melicoin = melicoin;
    }

    public Object getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Object discounts) {
        this.discounts = discounts;
    }

    public DifferentialPricing getDifferential_pricing() {
        return differential_pricing;
    }

    public void setDifferential_pricing(DifferentialPricing differential_pricing) {
        this.differential_pricing = differential_pricing;
    }

    public boolean isCatalog_listing() {
        return catalog_listing;
    }

    public void setCatalog_listing(boolean catalog_listing) {
        this.catalog_listing = catalog_listing;
    }

    @Override
    public String toString() {
        return "<p><strong>Id. de Producto: </strong>"+getId()+"<br>"+
                "<strong>Estado del producto: </strong>"+getCondition()+"<br>"+
                "<strong>Tipo de envio: </strong>"+(getShipping().isFree_shipping()?"Gratis":"Acordado con el vendedor")+"<br>"+
                "<strong>Cantidades vendidas: </strong>"+getSold_quantity()+"<br>"+
                "<strong>Cantidades en stock: </strong>"+getAvailable_quantity()+"<br>"+
                "<strong>Id del Vendedor: </strong>"+ getSeller().getId()+"<br>"+
                "<strong>Ubicación del vendedor: </strong>"+getSeller_address().getCity().getName()+", "+getSeller_address().getState().getName()+"<br>"+
                "</p>";
    }
}
