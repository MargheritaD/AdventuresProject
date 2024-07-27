package com.example.adventures.model;

public class Traveler extends GenericUser{
    private int idViaggiatore;
    private  String nomeViaggiatore;
    private String cognomeViaggiatore;
    private String email;

    public Traveler() {}
    public Traveler(int idViaggiatore, String nomeViaggiatore, String cognomeViaggiatore, String email){
        this.idViaggiatore = idViaggiatore;
        this.nomeViaggiatore = nomeViaggiatore;
        this.cognomeViaggiatore = cognomeViaggiatore;
        this.email = email;
    }

    public String getName() {
        return nomeViaggiatore;
    }

    @Override
    public int getId(){
        return idViaggiatore;
    }

    public String getSurname() {
        return cognomeViaggiatore;
    }

    @Override
    public String getEmail(){
        return email;}

}
