package com.example.adventures.bean;

public class TravelerBean {

    private int idTraveler;
    private final String travelerName;
    private final String travelerSurname;
    private String phone;
    private String email;
    private int travelerId;

    public TravelerBean(int id, String name, String surname, String phone, String email) {
        this.idTraveler = id;
        this.travelerName = name;
        this.travelerSurname = surname;
        this.travelerId = id;
        this.phone = phone;
        this.email = email;
    }

    public TravelerBean(int id, String name, String surname, String email) {
        this.travelerName = name;
        this.travelerSurname = surname;
        this.travelerId = id;
        this.email = email;
    }

    public String getName() {
        return travelerName;
    }

    public void setId(int idTraveler) {
        this.idTraveler = idTraveler;
    }

    public int getId(){return travelerId;}

    public String getEmail(){return email;}
}
