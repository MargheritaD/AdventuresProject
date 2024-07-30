package com.example.adventures.model;

public class LocationInfo {
    private String departureCity;
    private String country;

    public LocationInfo(String departureCity, String country) {
        this.departureCity = departureCity;
        this.country = country;
    }

    // Getter e Setter
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
