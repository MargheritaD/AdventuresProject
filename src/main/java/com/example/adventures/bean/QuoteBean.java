package com.example.adventures.bean;

import com.example.adventures.engineering.decoretor.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteBean {

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
