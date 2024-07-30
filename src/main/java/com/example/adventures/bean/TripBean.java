package com.example.adventures.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TripBean {

    private int idTrip;
    private String tripName;
    private LocationInfoBean locationInfo;
    private PeriodInfoBean periodInfo;
    private String category;
    private String price;
    private String guide;
    private List<ItineraryStopBean> stops = new ArrayList<>();

    // ci stava anche il costruttore con 8 parametri


    public TripBean(int idTrip, String tripName, LocationInfoBean locationInfo, PeriodInfoBean periodInfo, String category, String price, String guide) {
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.locationInfo = locationInfo;
        this.periodInfo = periodInfo;
        this.category = category;
        this.price = price;
        this.guide = guide;
    }

    public  TripBean(int idTrip, String tripName, LocationInfoBean locationInfo, PeriodInfoBean periodInfo, String guide){
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.locationInfo = locationInfo;
        this.periodInfo = periodInfo;
        this.guide = guide;
    }

    public  TripBean(String tripName, LocationInfoBean locationInfo, PeriodInfoBean periodInfo, String category, String price){
        this.tripName = tripName;
        this.locationInfo = locationInfo;
        this.periodInfo = periodInfo;
        this.category = category;
        this.price = price;
    }

    public TripBean(String tripName, PeriodInfoBean periodInfoBean){
        this.tripName = tripName;
        this.periodInfo = periodInfoBean;
    }

    public TripBean(int idTrip){
        this.idTrip = idTrip;
    }

    public LocationInfoBean getLocationInfo() {
        return locationInfo;
    }
    public void setLocationInfo(LocationInfoBean locationInfo) {
        this.locationInfo = locationInfo;
    }

    public PeriodInfoBean getPeriodInfo() {
        return periodInfo;
    }

    public void setPeriodInfo(PeriodInfoBean periodInfo) {
        this.periodInfo = periodInfo;
    }

    public LocalDate getOutboundDate() {
        return periodInfo.getOutboundDate();
    }

    public void setOutboundDate(LocalDate outboundDate) {
        periodInfo.setOutboundDate(outboundDate);
    }

    public LocalDate getReturnDate(){
        return periodInfo.getReturnDate();
    }

    public void setReturnDate(LocalDate returnDate) {
        periodInfo.setReturnDate(returnDate);
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
        return locationInfo.getDepartureCity();
    }

    public void setDepartureCity(String departureCity) {
        locationInfo.setDepartureCity(departureCity);
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

    public String getCountry(){
        return locationInfo.getCountry();
    }

    public void setCountry(String country){
        locationInfo.setCountry(country);
    }


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
