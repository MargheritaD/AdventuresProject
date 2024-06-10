package com.example.adventures.bean;

import java.time.LocalDate;

public class CountryCategoryBean {
    private String category;
    private String country;

    public CountryCategoryBean(String country, String category){
        this.category = category;
        this.country = country;
    }
    public CountryCategoryBean(){

    }


    public String getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
