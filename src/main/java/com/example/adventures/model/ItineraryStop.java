package com.example.adventures.model;

import java.time.LocalDate;

public class ItineraryStop {

    private String city;
    private LocalDate startDate;
    private LocalDate endDate;

    public ItineraryStop(String city, LocalDate startDate, LocalDate endDate){
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCitta(String city) {
        this.city = city;
    }

    public LocalDate getArrival() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeparture() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
