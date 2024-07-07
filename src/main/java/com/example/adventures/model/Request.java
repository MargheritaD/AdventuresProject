package com.example.adventures.model;

public class Request {

    private int requestId;

    private int guieId;

    private int idTrip;

    private int idTraveler;

    private String nomeViaggio;

    private String nomeViaggiatore;

    private String cognomeViaggiatore;

    private Status status;

    public Request(int idTrip, int idTraveler){
        this.idTrip = idTrip;
        this.idTraveler = idTraveler;
    }

    public Request(String nomeViaggio, String nomeViaggiatore, String cognomeViaggiatore, int requestId){
        this.nomeViaggio = nomeViaggio;
        this.nomeViaggiatore = nomeViaggiatore;
        this.cognomeViaggiatore = cognomeViaggiatore;
        this.requestId = requestId;
    }

    public int getIdTrip(){
        return idTrip;
    }

    public int getIdTraveler(){
        return idTraveler;
    }

    public String getCognomeViaggiatore() {
        return cognomeViaggiatore;
    }

    public String getNomeViaggiatore() {
        return nomeViaggiatore;
    }

    public String getNomeViaggio() {
        return nomeViaggio;
    }

    public int getRequestId() {
        return requestId;
    }
}
