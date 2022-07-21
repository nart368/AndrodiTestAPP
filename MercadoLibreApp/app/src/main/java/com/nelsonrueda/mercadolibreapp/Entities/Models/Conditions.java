package com.nelsonrueda.mercadolibreapp.Entities.Models;

import java.util.ArrayList;
import java.util.Date;

public class Conditions {
    private ArrayList<String> context_restrictions;
    private Date start_time;
    private Date end_time;
    private boolean eligible;

    public Conditions(){}


    public ArrayList<String> getContext_restrictions() {
        return context_restrictions;
    }

    public void setContext_restrictions(ArrayList<String> context_restrictions) {
        this.context_restrictions = context_restrictions;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
}
