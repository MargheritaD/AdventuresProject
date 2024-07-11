package com.example.adventures.model;

public class Traveler extends GenericUser{
    private int idViaggiatore;
    private static String nomeViaggiatore;
    private String cognomeViaggiatore;
    private String email;

    public Traveler() {}
    public Traveler(int idViaggiatore, String nomeViaggiatore, String cognomeViaggiatore, String email){
        this.idViaggiatore = idViaggiatore;
        this.nomeViaggiatore = nomeViaggiatore;
        this.cognomeViaggiatore = cognomeViaggiatore;
        this.email = email;
    }

    public static String getName() {
        return nomeViaggiatore;
    }

    @Override
    public int getId(){
        return idViaggiatore;
    }

    public String getSurname() {
        return cognomeViaggiatore;
    }
    public String getNomeViaggiatore(){return nomeViaggiatore;}

    @Override
    public String getEmail(){
        return email;}

}
