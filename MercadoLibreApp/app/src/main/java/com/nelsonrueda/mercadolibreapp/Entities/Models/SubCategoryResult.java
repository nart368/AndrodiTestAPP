package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;
import java.util.Date;

public class SubCategoryResult {
    private String id;
    private String name;
    private String picture;
    private String permalink;
    private int total_items_in_this_category;
    private ArrayList<PathFromRoot> path_from_root;
    private ArrayList<ChildrenCategory> children_categories;
    private String attribute_types;
    private Settings settings;
    private ArrayList<ChannelsSetting> channels_settings;
    private Object meta_categ_id;
    private boolean attributable;
    private Date date_created;

    public SubCategoryResult(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public int getTotal_items_in_this_category() {
        return total_items_in_this_category;
    }

    public void setTotal_items_in_this_category(int total_items_in_this_category) {
        this.total_items_in_this_category = total_items_in_this_category;
    }

    public ArrayList<PathFromRoot> getPath_from_root() {
        return path_from_root;
    }

    public void setPath_from_root(ArrayList<PathFromRoot> path_from_root) {
        this.path_from_root = path_from_root;
    }

    public ArrayList<ChildrenCategory> getChildren_categories() {
        return children_categories;
    }

    public void setChildren_categories(ArrayList<ChildrenCategory> children_categories) {
        this.children_categories = children_categories;
    }

    public String getAttribute_types() {
        return attribute_types;
    }

    public void setAttribute_types(String attribute_types) {
        this.attribute_types = attribute_types;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public ArrayList<ChannelsSetting> getChannels_settings() {
        return channels_settings;
    }

    public void setChannels_settings(ArrayList<ChannelsSetting> channels_settings) {
        this.channels_settings = channels_settings;
    }

    public Object getMeta_categ_id() {
        return meta_categ_id;
    }

    public void setMeta_categ_id(Object meta_categ_id) {
        this.meta_categ_id = meta_categ_id;
    }

    public boolean isAttributable() {
        return attributable;
    }

    public void setAttributable(boolean attributable) {
        this.attributable = attributable;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
}
