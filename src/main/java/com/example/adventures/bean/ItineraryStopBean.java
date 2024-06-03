package com.example.adventures.bean;

import java.time.LocalDate;

public class ItineraryStopBean {

    //private int idViaggio;
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;

    public ItineraryStopBean(String city, LocalDate startDate, LocalDate endDate) {
        //this.idViaggio = idViaggio;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getArrival(){ return startDate;}

    public LocalDate getDeparture(){return endDate;}
}
