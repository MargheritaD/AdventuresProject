package com.example.adventures.bean;

public class RequestBean {

    private int idTrip;
    private int idTraveler;

    public RequestBean(int idTrip, int idTraveler){
        this.idTraveler = idTraveler;
        this.idTrip = idTrip;
    }

    public int getIdTrip(){return idTrip;}

    public int getIdTraveler(){return idTraveler;}
}
