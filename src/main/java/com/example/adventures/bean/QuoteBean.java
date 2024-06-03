package com.example.adventures.bean;

import com.example.adventures.engineering.decoretor.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteBean {
/*
    private float healtcare;
    private float luggage;
    private float cancellation;
    private float tripPrice;

    //ORA
    private String insurance;

    public QuoteBean(float healtcare, float luggage, float cancellation, float tripPrice) {
        this.healtcare = healtcare;
        this.luggage = luggage;
        this.cancellation = cancellation;
        this.tripPrice = tripPrice;
    }

    public String getInsurance(){ return insurance; }
    public float getTripPrice() {
        return tripPrice;
    }

    public float getHealtcare() {
        return healtcare;
    }

    public float getLuggage() {
        return luggage;
    }

    public float getCancellation() {
        return cancellation;
    }

 */

    private float tripPrice;
    private List<Quote> selectedInsurances;

    public QuoteBean(float tripPrice) {
        this.tripPrice = tripPrice;
        this.selectedInsurances = new ArrayList<>();
    }

    public float getTripPrice() {
        return tripPrice;
    }

    public List<Quote> getSelectedInsurances() {
        return selectedInsurances;
    }

    public void addInsurance(Quote insurance) {
        this.selectedInsurances.add(insurance);
    }
}
