package com.example.adventures.model;

public class Request {

    private int requestId;

    private int guieId;

    private int idTrip;

    private int idTraveler;

    private String nomeViaggio;

    private String nomeViaggiatore;

    private String cognomeViaggiatore;

    //prima invece di scrivere intero ci stava come tipo lo stato (sotto)
    private int status;

    public Request(int idTrip, int idTraveler){
        this.idTrip = idTrip;
        this.idTraveler = idTraveler;
    }

    public Request(String nomeViaggio, int requestId, int status){
        this.nomeViaggio = nomeViaggio;
        this.requestId = requestId;
        this.status = status;
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
    public int getStatus(){return status;}

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
