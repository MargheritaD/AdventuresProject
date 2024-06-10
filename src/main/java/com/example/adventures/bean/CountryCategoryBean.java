package com.example.adventures.bean;

import java.time.LocalDate;

public class CountryCategoryBean {
    private String category;
    private String country;

    public CountryCategoryBean(String country, String category){
        this.category = category;
        this.country = country;
    }
}
