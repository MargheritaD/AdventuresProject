package com.example.adventures.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private int idTrip;
    private String tripName;
    private LocationInfo locationInfo;
    private PeriodInfo periodInfo;
    private String category;
    private String price;
    private String guide;
    private List<ItineraryStop> stops;

    // Costruttore predefinito che inizializza la lista di fermate
    public Trip() {
        this.stops = new ArrayList<>(); // Inizializzazione della lista
    }

    // Costruttore completo con 7 parametri
    public Trip(String tripName, LocationInfo locationInfo, String category, PeriodInfo periodInfo, String price, String guide) {
        this();
        this.tripName = tripName;
        this.locationInfo = locationInfo;
        this.category = category;
        this.periodInfo = periodInfo;
        this.price = price;
        this.guide = guide;
    }

    public Trip(String tripName, PeriodInfo periodInfo) {
        this();
        this.tripName = tripName;
        this.periodInfo = periodInfo;
    }

    // Costruttore che include l'ID e la guida
    public Trip(int idTrip, String tripName, PeriodInfo periodInfo, String guide) {
        this();
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.periodInfo = periodInfo;
        this.guide = guide;
    }

    // Costruttore che include l'ID e la città di partenza
    public Trip(int idTrip, String tripName, PeriodInfo periodInfo) {
        this();
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.periodInfo = periodInfo;
    }

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDepartureCity() {
        return locationInfo != null ? locationInfo.getDepartureCity() : null;
    }

    public void setDepartureCity(String departureCity) {
        if (locationInfo == null) {
            locationInfo = new LocationInfo(departureCity, null);
        } else {
            locationInfo.setDepartureCity(departureCity);
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getOutboundDate() {

        return periodInfo != null ? periodInfo.getOutboundDate() : null;

    }

    public void setOutboundDate(LocalDate outboundDate) {
        if (periodInfo == null) {
            periodInfo = new PeriodInfo(outboundDate, null);
        } else {
            periodInfo.setOutboundDate(outboundDate);
        }
    }

    public LocalDate getReturnDate() {
        return periodInfo != null ? periodInfo.getReturnDate() : null;
    }

    public void setReturnDate(LocalDate returnDate) {
        if (periodInfo == null) {
            periodInfo = new PeriodInfo(returnDate, null);
        } else {
            periodInfo.setOutboundDate(returnDate);
        }
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getCountry() {
        return locationInfo != null ? locationInfo.getCountry() : null;
    }

    public void setCountry(String country) {
        if (locationInfo == null) {
            locationInfo = new LocationInfo(null, country);
        } else {
            locationInfo.setCountry(country);
        }
    }

    public void setStops(List<ItineraryStop> stops) {
        ItineraryStop itineraryStop;
        this.stops = new ArrayList<>();
        for (ItineraryStop stop : stops){
            itineraryStop = new ItineraryStop(stop.getCity(), stop.getArrival(), stop.getDeparture());
            this.stops.add(itineraryStop);
        }
    }

    public List<ItineraryStop> getStops() {
        return stops;
    }

}


