package com.example.adventures.bean;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TripBean {

    private int idTrip;
    private String tripName;
    private String departureCity;
    private String category;
    private LocalDate outboundDate;
    private LocalDate returnDate;
    private String price;
    private String guide;
    private String country;
    private List<ItineraryStopBean> stops = new ArrayList<>();


    public TripBean(int idTrip, String tripName, String departureCity, String category,LocalDate outboundDate, LocalDate returnDate, String price, String guide){
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.departureCity = departureCity;
        this.category = category;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
        this.price = price;
        this.guide = guide;
    }

    public TripBean(String tripName, String departureCity, LocalDate outboundDate, LocalDate returnDate, String guide, String price){
        this.tripName = tripName;
        this.departureCity = departureCity;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
        this.price = price;
        this.guide = guide;
    }

    // Costruttore per i nuovi viaggi
    public TripBean(String tripName, String departureCity, String category,LocalDate outboundDate, LocalDate returnDate, String price, String country){
        this.tripName = tripName;
        this.departureCity = departureCity;
        this.category = category;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
        this.price = price;
        this.country = country;
    }

    public TripBean(String tripName, LocalDate outboundDate, LocalDate returnDate){
        this.tripName = tripName;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
    }

    public TripBean(int idTrip, String tripName,LocalDate outboundDate, LocalDate returnDate, String city, String guide){
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
        this.departureCity = city;
        this.guide = guide;
    }

    public TripBean(int idTrip){
        this.idTrip = idTrip;
    }

    public LocalDate getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(LocalDate outboundDate) {
        this.outboundDate = outboundDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGuide(){ return guide;}

    public void setGuide(String guide){
        this.guide = guide;
    }

    public String getCountry(){ return country;}

    public void setCountry(String country){this.country = country;}


    public void addStop(String city, LocalDate arrival, LocalDate departure) {
        this.stops.add(new ItineraryStopBean(city, arrival, departure));
    }

     public List<ItineraryStopBean> getStops() {
        return stops;
     }

     public void setStops(List<ItineraryStopBean> stops) {
        this.stops = stops;
     }

}
