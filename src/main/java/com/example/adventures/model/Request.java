package com.example.adventures.model;

public class Request {

    private int request_id;

    private int guieId;

    private int idTrip;

    private int idTraveler;

    private String nomeViaggio;

    private Status status;

    public Request(int idTrip, int idTraveler){
        this.idTrip = idTrip;
        this.idTraveler = idTraveler;
    }

    public int getIdTrip(){
        return idTrip;
    }

    public int getIdTraveler(){
        return idTraveler;
    }
}
