package com.example.adventures.bean;

public class LocationInfoBean {
    private String departureCity;
    private String country;

    public LocationInfoBean(String departureCity, String country) {
        this.departureCity = departureCity;
        this.country = country;
    }

    public LocationInfoBean(String departureCity){
        this.departureCity = departureCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
