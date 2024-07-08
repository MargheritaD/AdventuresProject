package com.example.adventures.bean;

public class RequestBean {

    private int idTrip;
    private int idTraveler;
    private String tripName;
    private String travelerName;
    private String travelerSurname;
    private int idRequest;
    private int status;

    public RequestBean(int idTrip, int idTraveler){
        this.idTraveler = idTraveler;
        this.idTrip = idTrip;
    }

    public RequestBean(String tripName, int idRequest, int status){
        this.tripName = tripName;
        this.idRequest = idRequest;
        this.status = status;
    }

    public RequestBean(int idTrip, int idTraveler, String tripName, String travelerName){
        this.idTraveler = idTraveler;
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.travelerName = travelerName;
    }

    public RequestBean(String tripName, String travelerName, String travelerSurname, int idRequest){
        this.tripName = tripName;
        this.travelerName = travelerName;
        this.travelerSurname = travelerSurname;
        this.idRequest = idRequest;
    }

    public int getIdTrip(){return idTrip;}

    public int getIdRequest(){return idRequest;}

    public int getIdTraveler(){return idTraveler;}
    public int getStatus(){return status;}

    public void setTravelerName(String travelerName) {
        this.travelerName = travelerName;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public String getTravelerSurname() {
        return travelerSurname;
    }

    public void setTravelerSurname(String travelerSurname) {
        this.travelerSurname = travelerSurname;
    }

    public String getStatusString() {
        switch (status) {
            case 0:
                return "Pending";
            case 1:
                return "Accepted";
            case 2:
                return "Rejected";
            default:
                return "Unknown";
        }
    }
}
