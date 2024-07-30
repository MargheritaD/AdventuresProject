package com.example.adventures.model;

/*
public class Trip {

    private int idTrip;
    private String tripName;
    private String departureCity;
    private String category;
    private LocalDate outboundDate;
    private LocalDate returnDate;
    private String price;
    private String guide;
    private String country;
    private List<ItineraryStop> stops;


    // Costruttore predefinito che inizializza la lista di fermate
    public Trip() {
        this.stops = new ArrayList<>(); // Inizializzazione della lista
    }

    // Costruttore completo con 9 parametri

    // IN QUESTO QUI SOTTO LEVA GUIDE????


    public Trip(String tripName, String departureCity, String category, LocalDate outboundDate, LocalDate returnDate, String price, String guide, String country){
        this();
        this.tripName = tripName;
        this.departureCity = departureCity;
        this.category = category;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
        this.price = price;
        this.guide = guide;
        this.country = country;
    }


    // Costruttore per un viaggio con solo nome e date
    public Trip(String tripName, LocalDate outboundDate, LocalDate returnDate){
        this();
        this.tripName = tripName;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
    }

    // Costruttore che include l'ID e la guida
    public Trip(int idTrip, String tripName, LocalDate outboundDate, LocalDate returnDate, String guide){
        this();
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
        this.guide = guide;
    }

    // Costruttore che include l'ID e la città di partenza
    public Trip(int idTrip, String tripName, String departureCity,LocalDate outboundDate, LocalDate returnDate){
        this();
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.departureCity = departureCity;
        this.outboundDate = outboundDate;
        this.returnDate = returnDate;
    }

    public int getIdTrip(){
        return idTrip;
    }

    public void setIdTrip(int idTrip){
        this.idTrip = idTrip;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getTripName(){
        return tripName;
    }

    public void setTripName(String tripName){
        this.tripName = tripName;
    }

    public String getDepartureCity(){
        return departureCity;
    }

    public void setDepartureCity(String departureCity){
        this.departureCity = departureCity;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public LocalDate getOutboundDate(){
        return outboundDate;
    }

    public void setOutboundDate(LocalDate outboundDate){
        this.outboundDate = outboundDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }

    public String getGuide(){
        return guide;
    }

    public void setGuide(String guide){
        this.guide = guide;
    }

    public String getCountry(){ return country;}

    public void setCountry(String country){this.country = country;}

    // Clone degli oggetti ItineraryStop nella lista
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

 */

